package com.xande.api.product.product_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.xande.api.product.product_api.model.dto.ProductDto;

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

    @DBRef(lazy = false)
    private Category category;

    public static Product convert(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setProductIdentifier(productDto.getProductIdentifier());
        product.setNome(productDto.getNome());
        product.setDescricao(productDto.getDescricao());
        product.setPreco(productDto.getPreco());
        
        if (productDto.getCategory() != null) {
            product.setCategory(Category.convert(productDto.getCategory()));
        }
        return product;
    }
}
