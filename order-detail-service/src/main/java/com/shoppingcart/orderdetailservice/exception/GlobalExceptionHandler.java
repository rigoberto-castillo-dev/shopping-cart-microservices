package com.shoppingcart.orderdetailservice.exception;

import com.shoppingcart.orderdetailservice.dto.reponse.GeneralResponseDTO;
import com.shoppingcart.orderdetailservice.dto.reponse.OrderDetailResponseDTO;
import com.shoppingcart.orderdetailservice.constants.Constants;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<OrderDetailResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("[{}] - {}: {}", ex.getClass().getSimpleName(), Constants.BAD_REQUEST_MESSAGE, ex.getMessage());
        GeneralResponseDTO generalResponse = new GeneralResponseDTO(
                Constants.ERROR_CODE,
                Constants.BAD_REQUEST_MESSAGE
        );
        OrderDetailResponseDTO response= new  OrderDetailResponseDTO(generalResponse, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<OrderDetailResponseDTO> handleFeignException(FeignException ex) {
        log.error("[{}] - {}: {}", ex.getClass().getSimpleName(), Constants.ERROR_CONNECTING_TO_PRODUCT_SERVICE, ex.getMessage());
        GeneralResponseDTO generalResponse = new GeneralResponseDTO(
                Constants.ERROR_CODE,
                Constants.ERROR_CONNECTING_TO_PRODUCT_SERVICE
        );
        OrderDetailResponseDTO response = new OrderDetailResponseDTO(generalResponse, null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<OrderDetailResponseDTO> handleGeneralException(Exception ex) {
        log.error("[{}] - {}: {}", ex.getClass().getSimpleName(), Constants.INTERNAL_ERROR_MESSAGE, ex.getMessage());
        GeneralResponseDTO generalResponse = new GeneralResponseDTO(
               Constants.ERROR_CODE,
                Constants.ERROR_ORDER_DETAILS
        );
        OrderDetailResponseDTO response = new OrderDetailResponseDTO(generalResponse, null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
