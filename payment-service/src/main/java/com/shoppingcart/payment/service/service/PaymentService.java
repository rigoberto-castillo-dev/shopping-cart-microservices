package com.shoppingcart.payment.service.service;

import com.shoppingcart.payment.service.constants.Constants;
import com.shoppingcart.payment.service.dto.PaymentRequestDTO;
import com.shoppingcart.payment.service.dto.PaymentResponseDTO;
import com.shoppingcart.payment.service.entity.Payment;
import com.shoppingcart.payment.service.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Slf4j
public class PaymentService {
    private final PaymentRepository repository;
    private final Random random = new Random();

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<PaymentResponseDTO> processPayment(PaymentRequestDTO request) {

        if (request.getAmount() < 1) {
            log.warn(Constants.LOG_INVALID_PAYMENT_AMOUNT, request.getAmount(), request.getOrderId());
            throw new IllegalArgumentException(Constants.ERROR_INVALID_AMOUNT);
        }
        boolean paymentSuccess = random.nextBoolean();

        Payment payment = new Payment();
        payment.setOrderId(request.getOrderId());
        payment.setAmount(request.getAmount());
        payment.setSuccess(paymentSuccess);
        payment.setTimestamp(LocalDateTime.now());
        repository.save(payment);

        if (paymentSuccess) {
            log.info(Constants.LOG_PAYMENT_SUCCESS, request.getOrderId());
            return ResponseEntity.ok(new PaymentResponseDTO(Constants.STATUS_SUCCESS, Constants.RESPONSE_PAYMENT_SUCCESS, payment));
        } else {
            log.error(Constants.LOG_PAYMENT_FAILED, request.getOrderId());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PaymentResponseDTO(Constants.STATUS_BAD_REQUEST, Constants.RESPONSE_PAYMENT_FAILED, null));
        }
    }
}
