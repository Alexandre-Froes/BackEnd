package com.xande.api.product.product_api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xande.api.product.product_api.model.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {
    
}

