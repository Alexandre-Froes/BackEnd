package com.xande.api.shopping_api.model;

import com.xande.api.shopping_api.model.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String productIdentifier;
    private Double price;

    public static Item convert(ItemDto dto) {
        if (dto == null) return null;
        return new Item(dto.getProductIdentifier(), dto.getPrice());
    }

    public ItemDto toDto() {
        return new ItemDto(this.productIdentifier, this.price);
    }
}