package com.shoppingcart.orderdetailservice.model;

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
    private Long orderId;
    private Long productId;
    private int quantity;
    private double price;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String category;
    private String image;
}
