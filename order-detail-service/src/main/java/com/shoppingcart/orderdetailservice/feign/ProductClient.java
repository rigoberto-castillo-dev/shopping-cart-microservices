package com.shoppingcart.orderdetailservice.feign;

import com.shoppingcart.orderdetailservice.config.FeignConfig;
import com.shoppingcart.orderdetailservice.dto.ProductDTO;
import com.shoppingcart.orderdetailservice.dto.ProductResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8081/shoppingcart", configuration = FeignConfig.class)
public interface ProductClient {
    @GetMapping("/products/{id}")
    ProductResponseDTO getProductById(@PathVariable Long id);
}