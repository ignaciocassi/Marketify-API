package com.ignaciocassi.market.domain.service;

import com.ignaciocassi.market.domain.Category;
import com.ignaciocassi.market.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

//Servicio utilizado para implementar lógica de negocio y para desacoplar la capa de dominio de la capa de persistencia.

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<List<Category>> getAll() {
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int categoryId) {
        return categoryRepository.getCategory(categoryId);
    }

    public Optional<List<Category>> getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }

    public Optional<Category> getCategoryById(int categoryId) {
        return categoryRepository.getCategoryById(categoryId);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public boolean delete(int categoryId) {
        return getCategory(categoryId)
                .map(category -> {
                    categoryRepository.delete(categoryId);
                    return true;
                }).orElse(false);
    }

    public void toggleStatus(int categoryId) {
        categoryRepository.toggleStatus(categoryId);
    }
}
