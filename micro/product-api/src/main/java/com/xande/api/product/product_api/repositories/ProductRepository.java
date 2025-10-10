package com.xande.api.product.product_api.repositories;
    
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.xande.api.product.product_api.model.Product;

public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    
}
