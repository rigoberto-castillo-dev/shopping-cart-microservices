package com.shoppingcart.orderservice.service;


import com.shoppingcart.orderservice.constants.Constants;
import com.shoppingcart.orderservice.dto.*;
import com.shoppingcart.orderservice.feign.OrderDetailClient;
import com.shoppingcart.orderservice.feign.PaymentClient;
import com.shoppingcart.orderservice.model.Order;
import com.shoppingcart.orderservice.repository.OrderRepository;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {
    private final OrderRepository repository;
    private final PaymentClient paymentClient;
    private final OrderDetailClient orderDetailClient;

    public OrderService(OrderRepository repository, PaymentClient paymentClient, OrderDetailClient orderDetailClient) {
        this.repository = repository;
        this.paymentClient = paymentClient;
        this.orderDetailClient = orderDetailClient;
    }

    @Transactional
    public ResponseEntity<OrderResponseDTO<OrderDTO>> createOrder(OrderRequestDTO request) {

        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setTotalAmount(request.getTotalAmount());
        order.setPaid(false);
        order.setCreatedAt(LocalDateTime.now());
        repository.save(order);

        List<OrderDetailDTO> savedOrderDetails = null;

        if (request.getOrderDetails() != null && !request.getOrderDetails().isEmpty()) {
            try {
                List<OrderDetailRequestDTO> details = request.getOrderDetails().stream()
                        .map(detail -> new OrderDetailRequestDTO(order.getId(), detail.getProductId(), detail.getQuantity()))
                        .collect(Collectors.toList());

                ResponseEntity<OrderDetailResponseDTO<List<OrderDetailDTO>>> detailResponse = orderDetailClient.saveOrderDetails(details);

                if (detailResponse.getStatusCode().is2xxSuccessful()) {
                    savedOrderDetails = detailResponse.getBody().getData();
                } else {
                    log.warn(Constants.ERROR_CONNECTING_TO_ORDER_DETAIL_SERVICE);
                }
            } catch (FeignException e) {
                log.error(Constants.ERROR_CONNECTING_TO_ORDER_DETAIL_SERVICE, e.getMessage());
            }
        }

        PaymentRequestDTO paymentRequest = new PaymentRequestDTO(order.getId(), request.getTotalAmount());
        PaymentResponseDTO paymentResponse;

        try {
            paymentResponse = paymentClient.processPayment(paymentRequest);
        } catch (FeignException e) {
            log.error(Constants.ERROR_CONNECTING_TO_PAYMENT_SERVICE, e.getMessage());
            return ResponseEntity.status(Constants.INTERNAL_SERVER_ERROR)
                    .body(new OrderResponseDTO<>(Constants.INTERNAL_SERVER_ERROR,
                            Constants.ERROR_CONNECTING_TO_PAYMENT_SERVICE, null));
        }

        if (paymentResponse.getCode() == Constants.SUCCESS) {
            order.setPaid(true);
            repository.save(order);
        }

        OrderDTO orderDTO = new OrderDTO(order.getId(), request.getUserId(), request.getTotalAmount(), order.isPaid(), savedOrderDetails);

        return ResponseEntity.status(Constants.CREATED)
                .body(new OrderResponseDTO<>(Constants.CREATED, Constants.ORDER_SAVED, orderDTO));
    }

    public ResponseEntity<OrderResponseDTO<OrderDTO>> getOrderById(Long orderId) {
        log.info("Fetching order with ID: {}", orderId);

        return repository.findById(orderId)
                .map(order -> {
                    OrderDTO orderDTO = new OrderDTO(order.getId(), order.getUserId(), order.getTotalAmount(), order.isPaid(), null);

                    try {
                        OrderDetailResponseDTO<List<OrderDetailDTO>> detailResponse = orderDetailClient.getOrderDetailsByOrderId(orderId);
                        orderDTO.setOrderDetails(detailResponse.getData());
                    } catch (FeignException.NotFound e) {
                        log.warn(Constants.ORDER_DETAILS_NOT_FOUND, orderId);
                    } catch (FeignException e) {
                        log.error(Constants.ERROR_CONNECTING_TO_ORDER_DETAIL_SERVICE, e.getMessage());
                    }

                    return ResponseEntity.ok(new OrderResponseDTO<>(Constants.SUCCESS, Constants.ORDER_FOUND, orderDTO));
                })
                .orElseGet(() -> {
                    log.warn(Constants.ORDER_NOT_FOUND, orderId);
                    return ResponseEntity.status(Constants.NOT_FOUND)
                            .body(new OrderResponseDTO<>(Constants.NOT_FOUND,
                                    String.format(Constants.ORDER_NOT_FOUND, orderId), null));
                });
    }
}
