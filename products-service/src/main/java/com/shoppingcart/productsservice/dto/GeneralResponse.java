package com.shoppingcart.productsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse {
    private String codeResponse;
    private String messageResponse;
}
