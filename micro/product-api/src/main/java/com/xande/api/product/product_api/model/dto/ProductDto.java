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
    private CategoryDto categoriaId;

    public static ProductDto convert(Product product) {
        return new ProductDto(
            product.getId(),
            product.getProductIdentifier(),
            product.getNome(),
            product.getDescricao(),
            product.getPreco(),
            CategoryDto.convert(product.getCategoriaId())
        );
    }
}
