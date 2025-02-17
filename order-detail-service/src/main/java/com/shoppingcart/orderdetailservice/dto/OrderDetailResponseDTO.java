package com.shoppingcart.orderdetailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDetailResponseDTO<T>  {
    private int code;
    private String message;
    private T data;
}
