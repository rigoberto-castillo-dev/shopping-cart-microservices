package com.shoppingcart.payment.service.exception;

import com.shoppingcart.payment.service.dto.response.GeneralResponseDTO;
import com.shoppingcart.payment.service.dto.response.PaymentResponseDTO;
import com.shoppingcart.payment.service.constants.Constants;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<PaymentResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("[{}] - {}: {}", ex.getClass().getSimpleName(), Constants.LOG_INVALID_PAYMENT_AMOUNT, ex.getMessage());
        GeneralResponseDTO generalResponse = new GeneralResponseDTO(
                Constants.STATUS_BAD_REQUEST,
                Constants.LOG_INVALID_PAYMENT_AMOUNT
        );
        PaymentResponseDTO response= new  PaymentResponseDTO(generalResponse, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<PaymentResponseDTO> handleFeignException(FeignException ex) {
        log.error("[{}] - {}: {}", ex.getClass().getSimpleName(), Constants.RESPONSE_PAYMENT_FAILED, ex.getMessage());
        GeneralResponseDTO generalResponse = new GeneralResponseDTO(
                Constants.STATUS_INTERNAL_SERVER_ERROR,
                Constants.RESPONSE_PAYMENT_FAILED
        );
        PaymentResponseDTO response = new PaymentResponseDTO(generalResponse, null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<PaymentResponseDTO> handleGeneralException(Exception ex) {
        log.error("[{}] - {}: {}", ex.getClass().getSimpleName(), Constants.RESPONSE_PAYMENT_FAILED, ex.getMessage());
        GeneralResponseDTO generalResponse = new GeneralResponseDTO(
                Constants.STATUS_INTERNAL_SERVER_ERROR,
                Constants.RESPONSE_PAYMENT_FAILED
        );
        PaymentResponseDTO response = new PaymentResponseDTO(generalResponse, null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
