package com.shoppingcart.orderservice.exception;

import com.shoppingcart.orderservice.dto.response.GeneralResponseDTO;
import com.shoppingcart.orderservice.dto.response.OrderDetailResponseDTO;
import com.shoppingcart.orderservice.constants.Constants;
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
    public ResponseEntity<OrderDetailResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("[{}] - {}: {}", ex.getClass().getSimpleName(), Constants.BAD_REQUEST_MESSAGE, ex.getMessage());
        GeneralResponseDTO generalResponse = new GeneralResponseDTO(
                Constants.BAD_REQUEST,
                Constants.BAD_REQUEST_MESSAGE
        );
        OrderDetailResponseDTO response= new  OrderDetailResponseDTO(generalResponse, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<OrderDetailResponseDTO> handleFeignException(FeignException ex) {
        log.error("[{}] - {}: {}", ex.getClass().getSimpleName(), Constants.ERROR_EXTERNAL_SERVICE, ex.getMessage());
        GeneralResponseDTO generalResponse = new GeneralResponseDTO(
                Constants.INTERNAL_SERVER_ERROR,
                Constants.ERROR_EXTERNAL_SERVICE
        );
        OrderDetailResponseDTO response = new OrderDetailResponseDTO(generalResponse, null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<OrderDetailResponseDTO> handleGeneralException(Exception ex) {
        log.error("[{}] - {}: {}", ex.getClass().getSimpleName(), Constants.ERROR_INTERNAL_SERVICE, ex.getMessage());
        GeneralResponseDTO generalResponse = new GeneralResponseDTO(
                Constants.INTERNAL_SERVER_ERROR,
                Constants.ERROR_INTERNAL_SERVICE
        );
        OrderDetailResponseDTO response = new OrderDetailResponseDTO(generalResponse, null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
