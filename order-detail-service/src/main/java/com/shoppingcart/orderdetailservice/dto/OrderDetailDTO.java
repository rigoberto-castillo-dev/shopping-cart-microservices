package com.shoppingcart.orderdetailservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    @NotNull(message = "Order ID is required")
    private Long orderId;

    @NotNull(message = "Product ID is required")
    private Long productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private double price;

    @NotBlank(message = "Product title is required")
    private String title;

    @NotBlank(message = "Product description is required")
    private String description;

    @NotBlank(message = "Product category is required")
    private String category;

    @NotBlank(message = "Product image URL is required")
    private String image;
}
