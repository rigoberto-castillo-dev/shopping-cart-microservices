package com.shoppingcart.orderservice.feign;

import com.shoppingcart.orderservice.dto.OrderDetailRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductClient {
    @GetMapping("api/products/{id}")
    OrderDetailRequestDTO getProductById(@PathVariable Long id);
}
