package com.shoppingcart.orderdetailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private Long orderId;
    private Long productId;
    private int quantity;
    private ProductDTO productDto;
}
