package com.shoppingcart.payment.service.service;

import com.shoppingcart.payment.service.constants.Constants;
import com.shoppingcart.payment.service.dto.PaymentDTO;
import com.shoppingcart.payment.service.dto.request.PaymentRequestDTO;
import com.shoppingcart.payment.service.dto.response.GeneralResponseDTO;
import com.shoppingcart.payment.service.dto.response.PaymentResponseDTO;
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

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<PaymentResponseDTO> processPayment(PaymentRequestDTO request) {

        boolean paymentSuccess = true;

        PaymentDTO paymentDto = new PaymentDTO();
        paymentDto.setOrderId(request.getOrderId());
        paymentDto.setCliente("Rigoberto Castillo");
        paymentDto.setPaymentMethod("PAYPAL");
        paymentDto.setAmount(request.getAmount());
        paymentDto.setSuccess(paymentSuccess);
        paymentDto.setTimestamp(LocalDateTime.now());

        Payment payment = new Payment();
        payment.setOrderId(paymentDto.getOrderId());
        payment.setAmount(paymentDto.getAmount());
        payment.setSuccess(paymentDto.isSuccess());
        payment.setTimestamp(paymentDto.getTimestamp());

        repository.save(payment);

        GeneralResponseDTO generalResponseDto;
        if (paymentSuccess) {
            log.info(Constants.LOG_PAYMENT_SUCCESS, request.getOrderId());
           generalResponseDto = (new GeneralResponseDTO(Constants.STATUS_SUCCESS, Constants.RESPONSE_PAYMENT_SUCCESS));
        } else {
            log.error(Constants.LOG_PAYMENT_FAILED, request.getOrderId());
            generalResponseDto = (new GeneralResponseDTO(Constants.RESPONSE_PAYMENT_FAILED, Constants.STATUS_INTERNAL_SERVER_ERROR));
        }
        return ResponseEntity.ok(new PaymentResponseDTO(generalResponseDto, paymentDto));
    }
}
