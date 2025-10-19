package com.xande.api.shopping_api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopDto {
    private String id;
    private String userIdentifier;

    private LocalDate date;

    private List<ItemDto> items = new ArrayList<>();
    private Double total;
}