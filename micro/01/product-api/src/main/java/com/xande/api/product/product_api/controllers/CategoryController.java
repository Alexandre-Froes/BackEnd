package com.xande.api.product.product_api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.xande.api.product.product_api.services.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import com.xande.api.product.product_api.model.dto.*;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping()
    public List<CategoryDto> getCategories() {
        return categoryService.getAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto create(@RequestBody @Valid CategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CategoryDto update(@PathVariable("id") String id, 
        @RequestBody @Valid CategoryDto categoryDto) {
        
        return categoryService.update(id, categoryDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        categoryService.delete(id);
    }

    @GetMapping("/pageable")
    public Page<CategoryDto> getCategoriesPage(Pageable page) {
        return categoryService.getAllPage(page);
    }    
}