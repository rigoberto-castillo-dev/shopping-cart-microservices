package com.shoppingcart.productsservice.controller;

import com.shoppingcart.productsservice.dto.ProductResponse;
import com.shoppingcart.productsservice.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shoppingcart/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductsService productsService;
    @GetMapping
    public ResponseEntity<ProductResponse> getAllProducts() {
        return ResponseEntity.ok(productsService.getAllProductsResponse());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productsService.getProductByIdResponse(id));
    }
}
