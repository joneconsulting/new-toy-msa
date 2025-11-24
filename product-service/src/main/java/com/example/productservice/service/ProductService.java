package com.example.productservice.service;

import com.example.productservice.jpa.ProductEntity;

public interface ProductService {
    Iterable<ProductEntity> getAllProducts();
}
