package com.shoppingcart.orderdetailservice.feign;

import com.shoppingcart.orderdetailservice.config.FeignConfig;
import com.shoppingcart.orderdetailservice.dto.reponse.ProductResponseDTO;
import com.shoppingcart.orderdetailservice.dto.request.ProductRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service", url = "${product.service.client.url}", configuration = FeignConfig.class)
public interface ProductClient {
    @PostMapping("/products/by-id")
    ProductResponseDTO getProductById(@RequestBody ProductRequestDTO productRequestDTO);
}