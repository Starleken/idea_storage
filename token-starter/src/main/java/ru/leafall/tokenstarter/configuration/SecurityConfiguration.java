package ru.leafall.tokenstarter.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.leafall.tokenstarter.component.AuthorizationFilter;
import ru.leafall.tokenstarter.service.TokenService;
import ru.leafall.tokenstarter.service.impl.AuthContextResolver;
import ru.leafall.tokenstarter.service.impl.TokenServiceImpl;

@Configuration
@EnableConfigurationProperties(SecurityConfigurationProps.class)
public class SecurityConfiguration {

    @Bean
    public TokenService tokenService(SecurityConfigurationProps props) {
        return new TokenServiceImpl(props.getKey());
    }

    @Bean
    public AuthContextResolver authContextResolver(SecurityConfigurationProps props) {
        return new AuthContextResolver(tokenService(props));
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthorizationFilter authorizationFilter(SecurityConfigurationProps props) {
        return new AuthorizationFilter(authContextResolver(props));
    }
}
