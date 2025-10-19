package com.xande.api.product.product_api.model.dto;

import com.xande.api.product.product_api.model.Category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    @Id
    private String id;
    @NotBlank(message = "O nome da categoria é obrigatório")
    private String nome;

    public static CategoryDto convert(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryDto(category.getId(), category.getNome());
    }
}