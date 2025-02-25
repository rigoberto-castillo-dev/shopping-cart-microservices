package com.shoppingcart.orderdetailservice.service;

import com.shoppingcart.orderdetailservice.constants.Constants;
import com.shoppingcart.orderdetailservice.dto.*;
import com.shoppingcart.orderdetailservice.dto.reponse.GeneralResponseDTO;
import com.shoppingcart.orderdetailservice.dto.reponse.OrderDetailResponseDTO;
import com.shoppingcart.orderdetailservice.dto.reponse.ProductResponseDTO;
import com.shoppingcart.orderdetailservice.dto.request.OrderDetailIdRequestDTO;
import com.shoppingcart.orderdetailservice.dto.request.OrderDetailRequestDTO;
import com.shoppingcart.orderdetailservice.dto.request.ProductRequestDTO;
import com.shoppingcart.orderdetailservice.model.OrderDetail;
import com.shoppingcart.orderdetailservice.feign.ProductClient;
import com.shoppingcart.orderdetailservice.repository.OrderDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public ResponseEntity<OrderDetailResponseDTO> saveOrderDetails(List<OrderDetailRequestDTO> details) {

        List<OrderDetailDTO> orderDetailDto = new ArrayList<>();
        List<OrderDetail> orderDetails = new ArrayList<>();

        for (OrderDetailRequestDTO dto : details) {
            ProductRequestDTO requestDTO = new ProductRequestDTO(dto.getProductId());
            ProductResponseDTO response = productClient.getProductById(requestDTO);

            if (response.getProductsDto() == null || response.getProductsDto().isEmpty()) {
                log.error(Constants.PRODUCT_NOT_FOUND);
                GeneralResponseDTO generalResponse = new GeneralResponseDTO(
                        Constants.ERROR_CODE, Constants.PRODUCT_NOT_FOUND + dto.getProductId()
                );
                return ResponseEntity.ok(new OrderDetailResponseDTO(generalResponse, orderDetailDto));
            }

            ProductDTO product = response.getProductsDto().get(0);

            OrderDetail detail = new OrderDetail();
            detail.setOrderId(dto.getOrderId());
            detail.setProductId(dto.getProductId());
            detail.setQuantity(dto.getQuantity());
            detail.setPrice(product.getPrice());
            detail.setTitle(product.getTitle());
            detail.setDescription(product.getDescription());
            detail.setCategory(product.getCategory());
            detail.setImage(product.getImage());

            orderDetails.add(detail);
        }

        repository.saveAll(orderDetails);

        List<OrderDetailDTO> responseDetails = orderDetails.stream().map(detail -> {
            ProductDTO productDto = new ProductDTO(
                    detail.getProductId(),
                    detail.getTitle(),
                    detail.getPrice(),
                    detail.getDescription(),
                    detail.getCategory(),
                    detail.getImage()
            );

            return new OrderDetailDTO(
                    detail.getOrderId(),
                    detail.getProductId(),
                    detail.getQuantity(),
                    productDto
            );
        }).collect(Collectors.toList());

        GeneralResponseDTO generalResponse = new GeneralResponseDTO(
                Constants.SUCCESS_CODE, Constants.ORDER_DETAILS_SAVED
        );

        return ResponseEntity.ok(new OrderDetailResponseDTO(generalResponse, responseDetails));
    }

    @Transactional
    public ResponseEntity<OrderDetailResponseDTO> getOrderDetailsByOrderId(OrderDetailIdRequestDTO orderDetailIdRequestDTO) {
        List<OrderDetail> orderDetails = repository.findByOrderId(orderDetailIdRequestDTO.getOrderId());
        if (orderDetails.isEmpty() || orderDetails == null) {
            GeneralResponseDTO generalResponse = new GeneralResponseDTO(
                    Constants.SUCCESS_CODE, Constants.ORDER_DETAILS_NOT_FOUND
            );
            return ResponseEntity.ok(new OrderDetailResponseDTO(generalResponse, new ArrayList<>()));
        }
        List<OrderDetailDTO> OrderDetailReponse = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            orderDetailDTO.setOrderId(orderDetail.getId());
            orderDetailDTO.setProductId(orderDetail.getProductId());
            orderDetailDTO.setQuantity(orderDetail.getQuantity());

            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(orderDetail.getProductId());
            productDTO.setTitle(orderDetail.getTitle());
            productDTO.setPrice(orderDetail.getPrice());
            productDTO.setDescription(orderDetail.getDescription());
            productDTO.setCategory(orderDetail.getCategory());
            productDTO.setImage(orderDetail.getImage());
            orderDetailDTO.setProductDto(productDTO);
            OrderDetailReponse.add(orderDetailDTO);
        }
        GeneralResponseDTO generalResponse = new GeneralResponseDTO(
                Constants.SUCCESS_CODE, Constants.ORDER_DETAILS_GET
        );
        return ResponseEntity.ok(new OrderDetailResponseDTO(generalResponse, OrderDetailReponse));
    }

}
