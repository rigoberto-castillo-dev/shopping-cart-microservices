package com.shoppingcart.productsservice.dto;

import com.shoppingcart.productsservice.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private GeneralResponse generalResponse;
    private List<Product> products = Collections.emptyList();;
}
