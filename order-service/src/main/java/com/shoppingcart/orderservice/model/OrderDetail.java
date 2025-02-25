package com.shoppingcart.orderservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "order_details")
@ToString
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    private Long productId;
    private int quantity;
    private double price;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String category;
    private String image;
}
