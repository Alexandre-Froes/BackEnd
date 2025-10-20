package com.xande.api.product.product_api.services;

import com.xande.api.product.product_api.model.Category;
import com.xande.api.product.product_api.model.Product;
import com.xande.api.product.product_api.model.dto.ProductDto;
import com.xande.api.product.product_api.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public List<ProductDto> getAll() {
        return repository.findAll().stream()
                .map(ProductDto::convert)
                .collect(Collectors.toList());
    }

    public Page<ProductDto> getAllPage(Pageable page) {
        Page<Product> productos = repository.findAll(page);
        return productos.map(ProductDto::convert);
    }

    public ProductDto findById(String id) {
        Product p = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        return ProductDto.convert(p);
    }

    public List<ProductDto> findByProductIdentifier(String productIdentifier) {
        List<Product> produtoEncontrado = repository.findAllByProductIdentifier(productIdentifier);
        if (produtoEncontrado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        return produtoEncontrado.stream().map(ProductDto::convert).collect(Collectors.toList());
    }

    public ProductDto save(ProductDto dto) {
        Product saved = repository.save(Product.convert(dto));
        return ProductDto.convert(saved);
    }

    public ProductDto update(String id, ProductDto dto) {
        Product produtoExistente = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        if (dto.getProductIdentifier() != null) produtoExistente.setProductIdentifier(dto.getProductIdentifier());
        if (dto.getNome() != null) produtoExistente.setNome(dto.getNome());
        if (dto.getDescricao() != null) produtoExistente.setDescricao(dto.getDescricao());
        if (dto.getPreco() != null) produtoExistente.setPreco(dto.getPreco());
        if (dto.getCategoriaId() != null) produtoExistente.setCategoriaId(Category.convert(dto.getCategoriaId()));

        Product updated = repository.save(produtoExistente);
        return ProductDto.convert(updated);
    }

    public void delete(String id) {
        Product p = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        repository.delete(p);
    }

    public List<ProductDto> getByCategoryId(String categoryId) {
        return repository.findAllByCategory(categoryId).stream()
                .map(ProductDto::convert)
                .collect(Collectors.toList());
    }
}