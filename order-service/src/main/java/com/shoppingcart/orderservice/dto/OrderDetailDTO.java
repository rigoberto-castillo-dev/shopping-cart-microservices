package com.shoppingcart.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private Long orderId;
    private Long productId;
    private int quantity;
    private double price;
    private String title;
    private String description;
    private String category;
    private String image;
}
