package com.xande.api.shopping_api.repositories;

import com.xande.api.shopping_api.model.Shop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShopRepository extends MongoRepository<Shop, String> {
    List<Shop> findByUserIdentifier(String userIdentifier);
    List<Shop> findByDate(LocalDate date);
    List<Shop> findByItemsProductIdentifier(String productIdentifier);

    List<Shop> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}