package com.shoppingcart.payment.service.controller;

import com.shoppingcart.payment.service.dto.request.PaymentRequestDTO;
import com.shoppingcart.payment.service.dto.response.PaymentResponseDTO;
import com.shoppingcart.payment.service.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingcart/payments")
public class PaymentController {
    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> processPayment(@RequestBody @Valid PaymentRequestDTO request) {
        return service.processPayment(request);
    }
}
