package com.shoppingcart.productsservice.feign;

import com.shoppingcart.productsservice.config.FeignConfig;
import com.shoppingcart.productsservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "fakeStoreClient", url = "${fake.store.client.url}", configuration = FeignConfig.class)
public interface FakeStoreClient {
    @GetMapping("/products")
    List<Product> getAllProducts();

    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable Long id);
}
