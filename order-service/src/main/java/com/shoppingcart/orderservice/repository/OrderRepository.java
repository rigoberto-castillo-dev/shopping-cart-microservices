package com.shoppingcart.orderservice.repository;

import com.shoppingcart.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
