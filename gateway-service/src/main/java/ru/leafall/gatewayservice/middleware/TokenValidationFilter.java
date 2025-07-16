package ru.leafall.gatewayservice.middleware;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.leafall.gatewayservice.dto.ResponseTokenDto;
import ru.leafall.gatewayservice.dto.Role;

import java.util.stream.Collectors;

import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
public class TokenValidationFilter extends AbstractGatewayFilterFactory<TokenValidationFilter.Config> {
    private final WebClient webClient;

    public TokenValidationFilter(WebClient.Builder builder) {
        this.webClient =  builder.baseUrl("http://account-service").build();
    }

    public static class Config {

    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            var request = exchange.getRequest();
            var path = request.getPath().toString();

            // Пропускаем запросы к account-service
            if (path.startsWith("/account-service/")) {
                return chain.filter(exchange);
            }

            var authHeader = request.getHeaders().getFirst(AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            var token = authHeader.substring(7);

            return webClient.post()
                    .uri("lb://account-service/v1/users/validate")
                    .header(AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(ResponseTokenDto.class)
                    .flatMap(response -> {
                        var mutatedRequest = exchange.getRequest().mutate()
                                .header("X-User-Id", response.getId().toString())
                                .header("X-User-Roles", response.getRoles().stream()
                                        .map(Role::name)
                                        .collect(Collectors.joining(",")))
                                .build();

                        return chain.filter(exchange.mutate().request(mutatedRequest).build());
                    })
                    .onErrorResume(e -> {
                        exchange.getResponse().setStatusCode(UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    });
        };
    }
}
