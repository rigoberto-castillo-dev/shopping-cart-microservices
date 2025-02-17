package com.shoppingcart.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    private Long productId;
    private String productName;
    private Double price;
    private Integer quantity;

}
