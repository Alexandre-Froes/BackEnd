package com.xande.api.product.product_api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xande.api.product.product_api.model.Category;
import com.xande.api.product.product_api.model.dto.CategoryDto;
import com.xande.api.product.product_api.repositories.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getAll() {
        List<Category> categorias = categoryRepository.findAll();

        return categorias.stream()
            .map(CategoryDto::convert)
            .collect(Collectors.toList());
    }

    public CategoryDto findById(ObjectId id) {
        Category categoria = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return CategoryDto.convert(categoria);
    }

    public CategoryDto save(CategoryDto categoryDto) {
        Category categoria = categoryRepository.save(Category.convert(categoryDto));
        return CategoryDto.convert(categoria);
    }

    public CategoryDto update(ObjectId id, CategoryDto categoryDto) {
        Category categoriaExistente = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        categoriaExistente.setNome(categoryDto.getNome());

        Category categoriaAtualizada = categoryRepository.save(categoriaExistente);
        return CategoryDto.convert(categoriaAtualizada);
    }

    public void delete(ObjectId id) {
        Category categoria = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        categoryRepository.delete(categoria);
    }

    public Page<CategoryDto> getAllPage(Pageable page) {
        Page<Category> categorias = categoryRepository.findAll(page);
        return categorias.map(CategoryDto::convert);
    }
}
