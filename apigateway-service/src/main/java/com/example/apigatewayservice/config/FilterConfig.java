package com.example.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

//@Configuration
public class FilterConfig {
    Environment env;

    public FilterConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public RouteLocator getRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/first-service/**")
                        .filters(f -> f.addRequestHeader("f-request", "1st-request-header-by-java")
                                .addResponseHeader("f-response", "1st-response-header-from-java"))
                        .uri("http://localhost:8081"))
                .build();
    }
}
