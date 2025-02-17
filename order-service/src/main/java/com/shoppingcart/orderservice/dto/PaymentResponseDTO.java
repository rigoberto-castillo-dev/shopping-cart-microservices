package com.shoppingcart.orderservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO<T> {
    private int code;
    private String message;
    private T data;
}
