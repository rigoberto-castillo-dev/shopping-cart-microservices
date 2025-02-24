package com.shoppingcart.productsservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    @NotNull(message = "ID cannot be null")
    @NotBlank(message = "ID cannot be blank")
    @NotEmpty(message = "ID cannot be empty")
    private String id;
}
