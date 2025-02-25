package com.shoppingcart.productsservice.service;

import com.shoppingcart.productsservice.constants.Constants;
import com.shoppingcart.productsservice.dto.ProductDTO;
import com.shoppingcart.productsservice.dto.response.GeneralResponseDTO;
import com.shoppingcart.productsservice.dto.response.ProductResponseDTO;
import com.shoppingcart.productsservice.dto.request.ProductRequestDTO;
import com.shoppingcart.productsservice.feign.FakeStoreClient;
import com.shoppingcart.productsservice.model.Product;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.shoppingcart.productsservice.constants.Constants.*;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private static final Logger logger = LoggerFactory.getLogger(ProductsService.class);
    private final FakeStoreClient fakeStoreClient;

    public ProductResponseDTO getAllProductsResponse() {
        GeneralResponseDTO generalResponseDto;
        List<Product> products = fakeStoreClient.getAllProducts();
        if (products == null || products.isEmpty()) {
            generalResponseDto = new GeneralResponseDTO(ERROR_CODE, NOT_RESULTS_MESSSAGE);
            return new ProductResponseDTO(generalResponseDto, new ArrayList<>());
        }

        List<ProductDTO> productDto = products.stream().map(product -> new ProductDTO(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory(),
                product.getImage()
        )).collect(Collectors.toList());

        generalResponseDto = new GeneralResponseDTO(SUCCESS_CODE, SUCCESS_MESSAGE);
        logger.info(Constants.SUCCESS_MESSAGE);
        return new ProductResponseDTO(generalResponseDto, productDto);
    }

    public ProductResponseDTO getProductByIdResponse(ProductRequestDTO productRequestDTO) {
        GeneralResponseDTO generalResponseDto;
        Product product = fakeStoreClient.getProductById(productRequestDTO.getProductId());
        if (product == null) {
            generalResponseDto = new GeneralResponseDTO(ERROR_CODE, NOT_RESULTS_MESSSAGE);
            return new ProductResponseDTO(generalResponseDto, new ArrayList<>());
        }
        ProductDTO productDto = new ProductDTO(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory(),
                product.getImage()
        );
        generalResponseDto = new GeneralResponseDTO(SUCCESS_CODE, SUCCESS_MESSAGE);
        logger.info(Constants.SUCCESS_MESSAGE);
        return new ProductResponseDTO(generalResponseDto, List.of(productDto));
    }
}
