package com.shoppingcart.orderservice.constants;

public class Constants {
    public static final String ORDER_SAVED = "Order saved successfully";
    public static final String ORDERS_RETRIEVED = "Orders retrieved successfully";
    public static final String ORDER_FOUND = "Order found successfully";
    public static final String ORDER_NOT_FOUND = "Order with ID %d not found";
    public static final String ERROR_SAVING_ORDER = "Error saving order: %s";
    public static final String ORDER_DETAILS_NOT_FOUND = "Order details for Order ID %d not found";
    public static final String ERROR_CONNECTING_TO_ORDER_DETAIL_SERVICE = "Error connecting to OrderDetailService: %s";
    public static final String ERROR_CONNECTING_TO_PAYMENT_SERVICE = "Error connecting to PaymentService: %s";

    public static final int SUCCESS = 200;
    public static final int CREATED = 201;
    public static final int BAD_REQUEST = 400;
    public static final int NOT_FOUND = 404;
    public static final int INTERNAL_SERVER_ERROR = 500;
}
