package com.shoppingcart.productsservice.exceptions;

import com.shoppingcart.productsservice.dto.response.GeneralResponseDTO;
import com.shoppingcart.productsservice.dto.response.ProductResponseDTO;
import com.shoppingcart.productsservice.constants.Constants;
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
    public ResponseEntity<ProductResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("[{}] - {}: {}", ex.getClass().getSimpleName(), Constants.INTERNAL_ERROR_MESSAGE, ex.getMessage());
        GeneralResponseDTO generalResponse = new GeneralResponseDTO(
                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                Constants.ERROR_MESSSAGE
        );
        ProductResponseDTO response = new ProductResponseDTO(generalResponse, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ProductResponseDTO> handleFeignException(FeignException ex) {
        log.error("[{}] - {}: {}", ex.getClass().getSimpleName(), Constants.EXTERNAL_SERVER_ERROR_MESSSAGE, ex.getMessage());
        GeneralResponseDTO generalResponse = new GeneralResponseDTO(
                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                Constants.ERROR_MESSSAGE
        );
        ProductResponseDTO response = new ProductResponseDTO(generalResponse, null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProductResponseDTO> handleGeneralException(Exception ex) {
        log.error("[{}] - {}: {}", ex.getClass().getSimpleName(), Constants.INTERNAL_ERROR_MESSAGE, ex.getMessage());
        GeneralResponseDTO generalResponse = new GeneralResponseDTO(
                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                Constants.ERROR_MESSSAGE
        );
        ProductResponseDTO response = new ProductResponseDTO(generalResponse, null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
