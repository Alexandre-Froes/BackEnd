package com.xande.api.shopping_api.controllers;

import com.xande.api.shopping_api.model.dto.ShopDto;
import com.xande.api.shopping_api.services.ShopService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @ResponseStatus(HttpStatus.CREATED)
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

    @GetMapping("/search")
    public List<ShopDto> search(
            @RequestParam(required = false) String userIdentifier,
            @RequestParam(required = false) String productIdentifier,
            @RequestParam(required = false) String date) {

        if (productIdentifier != null && !productIdentifier.isBlank()) {
            return service.findByProductIdentifier(productIdentifier);
        }

        if (userIdentifier != null && !userIdentifier.isBlank()) {
            return service.findByUserIdentifier(userIdentifier);
        }

        if (date != null && !date.isBlank()) {
            LocalDate d;
            try {
                d = LocalDate.parse(date);
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException("date must be yyyy-MM-dd");
            }
            return service.findByDate(d);
        }

        return service.findAll();
    }

    @GetMapping("/report")
    public ResponseEntity<?> report(
            @RequestParam String start,
            @RequestParam String end) {
        try {
            LocalDateTime s = LocalDateTime.parse(start);
            LocalDateTime e = LocalDateTime.parse(end);
            List<ShopDto> result = service.getReportBetween(s, e);
            return ResponseEntity.ok(result);
        } catch (DateTimeParseException ex) {
            return ResponseEntity.badRequest().body("start/end must be 2025-10-09T21:39:35.055)");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}