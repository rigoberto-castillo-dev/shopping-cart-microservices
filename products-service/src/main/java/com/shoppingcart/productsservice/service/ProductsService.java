package com.shoppingcart.productsservice.service;

import com.shoppingcart.productsservice.dto.GeneralResponse;
import com.shoppingcart.productsservice.dto.ProductResponse;
import com.shoppingcart.productsservice.feign.FakeStoreClient;
import com.shoppingcart.productsservice.model.Product;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static com.shoppingcart.productsservice.constants.Constants.*;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private static final Logger logger = LoggerFactory.getLogger(ProductsService.class);
    private final FakeStoreClient fakeStoreClient;

    public ProductResponse getAllProductsResponse() {
        try {
            GeneralResponse generalResponse;
            List<Product> products = fakeStoreClient.getAllProducts();
            if (products == null || products.isEmpty()) {
                generalResponse = new GeneralResponse(ERROR_CODE, NOT_RESULTS_MESSSAGE );
                return new ProductResponse(generalResponse, new ArrayList<>());
            }
            logger.info(SUCCESS_MESSAGE);
            generalResponse = new GeneralResponse(SUCCESS_CODE, SUCCESS_MESSAGE);
            return new ProductResponse(generalResponse, products);
        } catch (FeignException.NotFound ex) {
            logger.error(ERROR_MESSSAGE + ": {} - {}", ex.getClass().getName(), ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (FeignException.BadRequest ex) {
            logger.error(ERROR_MESSSAGE + ": {} - {}", ex.getClass().getName(), ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (FeignException.ServiceUnavailable ex) {
            logger.error(ERROR_MESSSAGE + ": {} - {}", ex.getClass().getName(), ex.getMessage());
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
        } catch (Exception ex) {
            logger.error(INTERNAL_ERROR_MESSSAGE + ": {} - {}", ex.getClass().getName(), ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex.getCause());
        }
    }

    public ProductResponse getProductByIdResponse(Long id) {
        try {
            GeneralResponse generalResponse;
            Product product = fakeStoreClient.getProductById(id);
            if (product == null) {
                generalResponse = new GeneralResponse(ERROR_CODE, NOT_RESULTS_MESSSAGE );
                return new ProductResponse(generalResponse, new ArrayList<>());
            }
            logger.info(SUCCESS_MESSAGE);
            generalResponse = new GeneralResponse(SUCCESS_CODE, SUCCESS_MESSAGE);
            return new ProductResponse(generalResponse, List.of(product));
        } catch (FeignException.NotFound ex) {
            logger.error(ERROR_MESSSAGE + ": {} - {}", ex.getClass().getName(), ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (FeignException.BadRequest ex) {
            logger.error(ERROR_MESSSAGE + ": {} - {}", ex.getClass().getName(), ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
