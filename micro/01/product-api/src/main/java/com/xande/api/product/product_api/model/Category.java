package com.xande.api.product.product_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.xande.api.product.product_api.model.dto.CategoryDto;

import lombok.Data;

@Data
@Document(collection = "categories")
public class Category {
    @Id
    private String id;
    private String nome;

    public static Category convert(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setNome(categoryDto.getNome());
        return category;
    }
}
