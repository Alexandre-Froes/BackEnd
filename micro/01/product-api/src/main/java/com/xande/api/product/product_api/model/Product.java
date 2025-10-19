package com.xande.api.product.product_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String productIdentifier;
    private String nome;
    private String descricao;
    private Double preco;

    @DBRef
    private Category categoriaId;

    public static Product convert(com.xande.api.product.product_api.model.dto.ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setProductIdentifier(productDto.getProductIdentifier());
        product.setNome(productDto.getNome());
        product.setDescricao(productDto.getDescricao());
        product.setPreco(productDto.getPreco());
        
        if (productDto.getCategoriaId() != null) {
            product.setCategoriaId(Category.convert(productDto.getCategoriaId()));
        }
        return product;
    }
}
