package com.shoppingcart.orderservice.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRequestDTO {
    private Long orderId;   // 🔹 ID de la orden
    private Long productId; // 🔹 ID del producto
    private int quantity;
}
