package com.shoppingcart.payment.service.repository;

import com.shoppingcart.payment.service.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
