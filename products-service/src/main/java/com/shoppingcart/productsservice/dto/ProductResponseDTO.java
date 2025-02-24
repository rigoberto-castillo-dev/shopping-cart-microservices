package com.shoppingcart.productsservice.dto;

import com.shoppingcart.productsservice.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private GeneralResponseDTO generalResponseDTO;
    private List<Product> products;
}
