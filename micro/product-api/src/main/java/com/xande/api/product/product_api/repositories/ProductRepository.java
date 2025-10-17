package com.xande.api.product.product_api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xande.api.product.product_api.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findAllByCategory(String category);

    List<Product> findAllByProductIdentifier(String productIdentifier);
}
