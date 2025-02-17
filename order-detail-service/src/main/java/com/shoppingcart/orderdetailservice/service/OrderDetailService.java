package com.shoppingcart.orderdetailservice.service;

import com.shoppingcart.orderdetailservice.constants.Constants;
import com.shoppingcart.orderdetailservice.dto.*;
import com.shoppingcart.orderdetailservice.model.OrderDetail;
import com.shoppingcart.orderdetailservice.feign.ProductClient;
import com.shoppingcart.orderdetailservice.repository.OrderDetailRepository;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderDetailService {

    private final OrderDetailRepository repository;
    private final ProductClient productClient;

    public OrderDetailService(OrderDetailRepository repository, ProductClient productClient) {
        this.repository = repository;
        this.productClient = productClient;
    }

    @Transactional
    public ResponseEntity<OrderDetailResponseDTO<List<OrderDetailDTO>>> saveOrderDetails(List<OrderDetailRequestDTO> details) {

        List<OrderDetail> orderDetails = details.stream().map(dto -> {
            try {
                // 🔹 Obtener la respuesta del servicio de productos
                ProductResponseDTO response = productClient.getProductById(dto.getProductId());

                // 🔹 Extraer el primer producto de la lista
                if (response.getProducts() == null || response.getProducts().isEmpty()) {
                    log.error("No product found for ID {}", dto.getProductId());
                    throw new RuntimeException("Product with ID " + dto.getProductId() + " not found");
                }

                ProductDTO product = response.getProducts().get(0);

                // 🔹 Crear el objeto `OrderDetail`
                OrderDetail detail = new OrderDetail();
                detail.setOrderId(dto.getOrderId());
                detail.setProductId(dto.getProductId());
                detail.setQuantity(dto.getQuantity());
                detail.setPrice(product.getPrice());
                detail.setTitle(product.getTitle());
                detail.setDescription(product.getDescription());
                detail.setCategory(product.getCategory());
                detail.setImage(product.getImage());

                return detail;
            } catch (FeignException.NotFound e) {
                log.error("Product with ID {} not found in ProductService", dto.getProductId());
                throw new RuntimeException("Product with ID " + dto.getProductId() + " not found");
            } catch (FeignException e) {
                log.error("Error connecting to ProductService: {}", e.getMessage());
                throw new RuntimeException("Error connecting to ProductService");
            }
        }).collect(Collectors.toList());

        repository.saveAll(orderDetails);

        // 🔹 Convertir la respuesta a DTO
        List<OrderDetailDTO> responseDetails = orderDetails.stream().map(detail ->
                new OrderDetailDTO(
                        detail.getOrderId(),
                        detail.getProductId(),
                        detail.getQuantity(),
                        detail.getPrice(),
                        detail.getTitle(),
                        detail.getDescription(),
                        detail.getCategory(),
                        detail.getImage()
                )).collect(Collectors.toList());

        return ResponseEntity.ok(new OrderDetailResponseDTO<>(201, "Order details saved successfully", responseDetails));
    }

    public ResponseEntity<OrderDetailResponseDTO<List<OrderDetailDTO>>> getOrderDetails(Long orderId) {
        List<OrderDetailDTO> details = repository.findByOrderId(orderId)
                .stream()
                .map(detail -> {
                    OrderDetailDTO dto = new OrderDetailDTO();
                    dto.setOrderId(detail.getOrderId());
                    dto.setProductId(detail.getProductId());
                    dto.setQuantity(detail.getQuantity());
                    dto.setPrice(detail.getPrice());
                    dto.setTitle(detail.getTitle());
                    dto.setDescription(detail.getDescription());
                    dto.setCategory(detail.getCategory());
                    dto.setImage(detail.getImage());
                    return dto;
                }).collect(Collectors.toList());

        if (details.isEmpty()) {
            return ResponseEntity.status(Constants.NOT_FOUND)
                    .body(new OrderDetailResponseDTO<>(Constants.NOT_FOUND,
                            String.format(Constants.ORDER_NOT_FOUND, orderId),
                            null));
        }

        return ResponseEntity.ok(new OrderDetailResponseDTO<>(Constants.SUCCESS, Constants.ORDER_DETAILS_RETRIEVED, details));
    }
}
