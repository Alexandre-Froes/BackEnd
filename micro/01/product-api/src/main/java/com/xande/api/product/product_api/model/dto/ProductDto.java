package com.xande.api.product.product_api.model.dto;

import org.springframework.data.annotation.Id;

import com.xande.api.product.product_api.model.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @Id
    private String id;
    private String productIdentifier;
    private String nome;
    private String descricao;
    private Double preco;
    private CategoryDto category;

    public static ProductDto convert(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setProductIdentifier(product.getProductIdentifier());
        dto.setNome(product.getNome());
        dto.setDescricao(product.getDescricao());
        dto.setPreco(product.getPreco());

        if (product.getCategory() != null) {
            dto.setCategory(CategoryDto.convert(product.getCategory()));
        } else {
            dto.setCategory(null);
        }
        return dto;
    }
}
