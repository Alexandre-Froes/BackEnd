package com.xande.api.product.product_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xande.api.product.product_api.services.ProductService;
import com.xande.api.product.product_api.model.dto.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public List<ProductDto> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable("id") String id) {
        return productService.findById(id);
    }

    @PostMapping()
    public ProductDto create(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable("id") String id, 
                    @RequestBody ProductDto productDto) {

        return productService.update(id, productDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        productService.delete(id);
    }

    @GetMapping("/pageable")
    public Page<ProductDto> getPageable(Pageable page) {
        return productService.getAllPage(page);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDto> getProductsByCategory(@PathVariable("categoryId") String categoryId) {
        return productService.getByCategoryId(categoryId);
    }
    
    @GetMapping("/{productIdentifier}/identifier")
    public List<ProductDto> getMethodName(@PathVariable("productIdentifier") String productIdentifier) {
        return productService.findByProductIdentifier(productIdentifier);
    }
    
    
    
}
