package com.shoppingcart.productsservice.controller;

import com.shoppingcart.productsservice.dto.response.ProductResponseDTO;
import com.shoppingcart.productsservice.dto.request.ProductRequestDTO;
import com.shoppingcart.productsservice.service.ProductsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shoppingcart/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductsService productsService;
    @GetMapping
    public ResponseEntity<ProductResponseDTO> getAllProducts() {
        return ResponseEntity.ok(productsService.getAllProductsResponse());
    }

    @PostMapping("/by-id")
    public ResponseEntity<ProductResponseDTO> getProductById(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(productsService.getProductByIdResponse(productRequestDTO));
    }
}
