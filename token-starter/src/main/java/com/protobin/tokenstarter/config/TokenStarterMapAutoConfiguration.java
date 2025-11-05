package com.protobin.tokenstarter.config;

import com.protobin.tokenstarter.component.TokenFilter;
import com.protobin.tokenstarter.service.AuthContextResolver;
import com.protobin.tokenstarter.service.TokenService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TokenStarterMapProperties.class)
public class TokenStarterMapAutoConfiguration {

    @Bean
    public TokenService tokenService(TokenStarterMapProperties properties) {
        return new TokenService(properties.getSecretKey());
    }

    @Bean
    public AuthContextResolver authContextResolver(TokenStarterMapProperties properties) {
        return new AuthContextResolver(tokenService(properties));
    }

    @Bean
    @ConditionalOnMissingBean
    public TokenFilter tokenFilter(TokenStarterMapProperties properties) {
        return new TokenFilter(authContextResolver(properties));
    }
}
