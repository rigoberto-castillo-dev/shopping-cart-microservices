package com.shoppingcart.orderdetailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private String codeResponse;
    private String messageResponse;
    private List<ProductDTO> products;
}
