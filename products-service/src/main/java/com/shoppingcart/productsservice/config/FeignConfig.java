package com.shoppingcart.productsservice.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
        };
    }
}
