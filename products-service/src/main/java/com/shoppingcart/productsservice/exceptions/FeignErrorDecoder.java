package com.shoppingcart.productsservice.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import static com.shoppingcart.productsservice.constants.Constants.*;

@Component
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus status = HttpStatus.resolve(response.status());
        switch (status) {
            case NOT_FOUND:
                return new ResponseStatusException(HttpStatus.NOT_FOUND, RESOURCE_NOT_FOUND_MESSSAGE);
            case BAD_REQUEST:
                return new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST_MESSSAGE);
            case INTERNAL_SERVER_ERROR:
                return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, EXTERNAL_SERVER_ERROR_MESSSAGE);
            default:
                return new ResponseStatusException(status, INTERNAL_ERROR_MESSSAGE);
        }
    }
}
