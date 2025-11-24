package com.example.productservice.controller;

import com.example.productservice.jpa.ProductEntity;
import com.example.productservice.service.ProductService;
import com.example.productservice.vo.ResponseProduct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product-service")
public class ProductController {
    Environment env;
    ProductService productService;

    @Autowired
    public ProductController(Environment env, ProductService productService) {
        this.env = env;
        this.productService = productService;
    }

    @GetMapping("/health-check")
    public String status() {
        return String.format("It's Working in Product Service on LOCAL PORT %s (SERVER PORT %s)",
                env.getProperty("local.server.port"),
                env.getProperty("server.port"));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ResponseProduct>> getProducts() {
        Iterable<ProductEntity> productList = productService.getAllProducts();

        List<ResponseProduct> result = new ArrayList<>();
        productList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseProduct.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
