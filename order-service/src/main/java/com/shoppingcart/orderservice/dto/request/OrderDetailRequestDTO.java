package com.shoppingcart.orderservice.dto.request;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRequestDTO {
    private Long orderId;
    private Long productId;
    private int quantity;
}
