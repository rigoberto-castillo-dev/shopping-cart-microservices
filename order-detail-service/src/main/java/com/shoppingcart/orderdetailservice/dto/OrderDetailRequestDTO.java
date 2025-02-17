package com.shoppingcart.orderdetailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRequestDTO {
    private Long orderId;
    private Long productId;
    private int quantity;
}
