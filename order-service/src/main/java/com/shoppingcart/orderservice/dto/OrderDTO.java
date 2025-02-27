package com.shoppingcart.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private Long userId;
    private double totalAmount;
    private boolean paid;
    private String cliente;
    private String paymentMethod;
    private String timestamp;
    private List<OrderDetailDTO> orderDetails;
}
