package com.shoppingcart.orderdetailservice.constants;

public class Constants {
    public static final String PRODUCT_NOT_FOUND = "Product with ID %d not found in ProductService";
    public static final String ERROR_CONNECTING_TO_PRODUCT_SERVICE = "Error connecting to ProductService: %s";
    public static final String ERROR_SAVING_ORDER_DETAILS = "Error saving order details: %s";
    public static final String ORDER_NOT_FOUND = "No order details found for Order ID: %d";

    public static final String ORDER_DETAILS_SAVED = "Order details saved successfully";
    public static final String ORDER_DETAILS_RETRIEVED = "Order details retrieved successfully";

    public static final int SUCCESS = 200;
    public static final int CREATED = 201;

    public static final int BAD_REQUEST = 400;
    public static final int NOT_FOUND = 404;
    public static final int INTERNAL_SERVER_ERROR = 500;

}
