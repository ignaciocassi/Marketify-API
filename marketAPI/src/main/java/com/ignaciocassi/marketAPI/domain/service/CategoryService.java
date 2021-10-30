package com.ignaciocassi.marketAPI.domain.service;

import com.ignaciocassi.marketAPI.domain.Category;
import com.ignaciocassi.marketAPI.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Servicio utilizado para implementar lógica de negocio y para desacoplar la capa de dominio de la capa de persistencia.

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<List<Category>> getAll() {
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int categoryId) {
        return categoryRepository.getCategory(categoryId);
    }

    public Optional<List<Category>> getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
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
