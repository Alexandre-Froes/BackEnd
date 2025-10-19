package com.xande.api.shopping_api.services;

import com.xande.api.shopping_api.model.Shop;
import com.xande.api.shopping_api.model.dto.ShopDto;
import com.xande.api.shopping_api.repositories.ShopRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShopService {
    private final ShopRepository repository;

    public ShopService(ShopRepository repository) {
        this.repository = repository;
    }

    public List<ShopDto> findAll() {
        return repository.findAll().stream().map(Shop::toDto).collect(Collectors.toList());
    }

    public Optional<ShopDto> findById(String id) {
        return repository.findById(id).map(Shop::toDto);
    }

    public ShopDto save(ShopDto dto) {
        if (dto.getId() == null || dto.getId().isBlank()) {
            dto.setId(UUID.randomUUID().toString());
        }
        Shop s = Shop.convert(dto);
        Shop saved = repository.save(s);
        return saved.toDto();
    }

    public List<ShopDto> findByUserIdentifier(String userIdentifier) {
        return repository.findByUserIdentifier(userIdentifier).stream().map(Shop::toDto).collect(Collectors.toList());
    }

    public List<ShopDto> findByDate(LocalDate date) {
        return repository.findByDate(date).stream().map(Shop::toDto).collect(Collectors.toList());
    }

    public List<ShopDto> findByProductIdentifier(String productIdentifier) {
        return repository.findByItemsProductIdentifier(productIdentifier).stream().map(Shop::toDto).collect(Collectors.toList());
    }
}