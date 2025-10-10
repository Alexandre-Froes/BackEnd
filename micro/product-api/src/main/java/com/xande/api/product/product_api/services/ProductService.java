package com.xande.api.product.product_api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xande.api.product.product_api.model.Category;
import com.xande.api.product.product_api.model.Product;
import com.xande.api.product.product_api.model.dto.ProductDto;
import com.xande.api.product.product_api.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
            .map(ProductDto::convert)
            .collect(Collectors.toList());
    }

    public ProductDto findById(ObjectId id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return ProductDto.convert(product);
    }

    public ProductDto save(ProductDto productDto) {
        Product product = productRepository.save(Product.convert(productDto));
        return ProductDto.convert(product);
    }

    public ProductDto update(ObjectId id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        existingProduct.setProductIdentifier(productDto.getProductIdentifier());
        existingProduct.setNome(productDto.getNome());
        existingProduct.setDescricao(productDto.getDescricao());
        existingProduct.setPreco(productDto.getPreco());
        existingProduct.setCategoriaId(Category.convert(productDto.getCategoriaId()));

        Product updatedProduct = productRepository.save(existingProduct);
        return ProductDto.convert(updatedProduct);
    }

    public void delete(ObjectId id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        productRepository.delete(product);
    }

    public Page<ProductDto> getAllPage(Pageable page) {
        Page<Product> products = productRepository.findAll(page);
        return products.map(ProductDto::convert);
    }

    
}
