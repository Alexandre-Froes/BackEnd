package com.xande.api.shopping_api.model;

import com.xande.api.shopping_api.model.dto.ShopDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "shops")
public class Shop {
    @Id
    private String id;
    private String userIdentifier;
    private LocalDate date;
    private List<Item> items = new ArrayList<>();
    private Double total;

    public static Shop convert(ShopDto dto) {
        if (dto == null) return null;
        Shop s = new Shop();
        s.setId(dto.getId());
        s.setUserIdentifier(dto.getUserIdentifier());
        s.setDate(dto.getDate());
        s.setTotal(dto.getTotal());
        if (dto.getItems() != null) {
            s.setItems(dto.getItems().stream()
                    .map(Item::convert)
                    .collect(Collectors.toList()));
        }
        return s;
    }

    public ShopDto toDto() {
        ShopDto dto = new ShopDto();
        dto.setId(this.id);
        dto.setUserIdentifier(this.userIdentifier);
        dto.setDate(this.date);
        dto.setTotal(this.total);
        dto.setItems(this.items == null ? new ArrayList<>() :
                this.items.stream().map(Item::toDto).collect(Collectors.toList()));
        return dto;
    }
}