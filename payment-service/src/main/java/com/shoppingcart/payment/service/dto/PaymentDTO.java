package com.shoppingcart.payment.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private Long orderId;
    private String cliente;
    private String paymentMethod;
    private double amount;
    private boolean success;
    private LocalDateTime timestamp;
}
