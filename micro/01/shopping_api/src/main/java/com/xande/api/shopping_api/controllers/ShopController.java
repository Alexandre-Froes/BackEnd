package com.xande.api.shopping_api.controllers;

import com.xande.api.shopping_api.model.dto.ShopDto;
import com.xande.api.shopping_api.services.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/shopping")
public class ShopController {
    private final ShopService service;

    public ShopController(ShopService service) {
        this.service = service;
    }

    @GetMapping
    public List<ShopDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopDto> findById(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ShopDto> save(@RequestBody ShopDto dto) {
        ShopDto saved = service.save(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/shopByUser")
    public List<ShopDto> getByUser(@RequestParam String userIdentifier) {
        return service.findByUserIdentifier(userIdentifier);
    }

    @GetMapping("/shopByDate")
    public ResponseEntity<?> getByDate(@RequestParam String date) {
        try {
            LocalDate d = LocalDate.parse(date);
            return ResponseEntity.ok(service.findByDate(d));
        } catch (DateTimeParseException ex) {
            return ResponseEntity.badRequest().body("date must be yyyy-MM-dd");
        }
    }

    @GetMapping("/product/{productIdentifier}")
    public List<ShopDto> findByProductIdentifier(@PathVariable String productIdentifier) {
        return service.findByProductIdentifier(productIdentifier);
    }
}