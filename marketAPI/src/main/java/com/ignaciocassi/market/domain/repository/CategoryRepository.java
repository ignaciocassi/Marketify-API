package com.ignaciocassi.market.domain.repository;

import com.ignaciocassi.market.domain.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    //Especificación del repositorio.
    //Define las operaciones que serán utilizadas en la capa de dominio y que deben ser implementadas
    //Por el respositorio.

    Optional<List<Category>> getAll();

    Optional<Category> getCategory(int categoryId);

    Optional<List<Category>> getCategoryByName(String name);

    Category save(Category category);

    void delete(int categoryId);

    void toggleStatus(int categoryId);
}
