package com.example.apiserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableResourceServer
public class ApiServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .tokenServices(tokenService());
    }

    @Bean
    public RemoteTokenServices tokenService() {
        RemoteTokenServices services = new RemoteTokenServices();

        services.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
        services.setClientId("client");
        services.setClientSecret("secret");
        services.setRestTemplate(restTemplate());

        return services;
    }

    @Bean
    public JwtAccessTokenConverter tokenConverter() {
        return new JwtAccessTokenConverter();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
