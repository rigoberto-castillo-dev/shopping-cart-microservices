package com.shoppingcart.payment.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentResponseDTO<T> {
    private int code;
    private String message;
    private T data;;
}
