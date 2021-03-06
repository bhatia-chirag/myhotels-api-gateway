package com.myhotels.springcloudapigateway.configs;

import com.myhotels.springcloudapigateway.filters.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service",
                        r -> r.path("/auth/**")
                                .filters(f -> f.filter(filter))
                                .uri("http://localhost:9000/auth"))
                .build();
    }
}
