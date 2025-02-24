package com.shoppingcart.productsservice.service;

import com.shoppingcart.productsservice.constants.Constants;
import com.shoppingcart.productsservice.dto.GeneralResponseDTO;
import com.shoppingcart.productsservice.dto.ProductResponseDTO;
import com.shoppingcart.productsservice.dto.ProductRequestDTO;
import com.shoppingcart.productsservice.feign.FakeStoreClient;
import com.shoppingcart.productsservice.model.Product;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import static com.shoppingcart.productsservice.constants.Constants.*;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private static final Logger logger = LoggerFactory.getLogger(ProductsService.class);
    private final FakeStoreClient fakeStoreClient;

    public ProductResponseDTO getAllProductsResponse() {
        GeneralResponseDTO generalResponseDTO;
        List<Product> products = fakeStoreClient.getAllProducts();
        if (products == null || products.isEmpty()) {
            generalResponseDTO = new GeneralResponseDTO(ERROR_CODE, NOT_RESULTS_MESSSAGE);
            return new ProductResponseDTO(generalResponseDTO, new ArrayList<>());
        }
        generalResponseDTO = new GeneralResponseDTO(SUCCESS_CODE, SUCCESS_MESSAGE);
        logger.info(Constants.SUCCESS_MESSAGE);
        return new ProductResponseDTO(generalResponseDTO, products);
    }

    public ProductResponseDTO getProductByIdResponse(ProductRequestDTO productRequestDTO) {
        GeneralResponseDTO generalResponseDTO;
        Product product = fakeStoreClient.getProductById(productRequestDTO.getId());
        if (product == null) {
            generalResponseDTO = new GeneralResponseDTO(ERROR_CODE, NOT_RESULTS_MESSSAGE);
            return new ProductResponseDTO(generalResponseDTO, new ArrayList<>());
        }
        generalResponseDTO = new GeneralResponseDTO(SUCCESS_CODE, SUCCESS_MESSAGE);
        logger.info(Constants.SUCCESS_MESSAGE);
        return new ProductResponseDTO(generalResponseDTO, List.of(product));
    }
}
