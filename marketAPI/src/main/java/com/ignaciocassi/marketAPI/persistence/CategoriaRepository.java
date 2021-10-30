package com.ignaciocassi.marketAPI.persistence;

import com.ignaciocassi.marketAPI.domain.Category;
import com.ignaciocassi.marketAPI.domain.repository.CategoryRepository;
import com.ignaciocassi.marketAPI.persistence.crud.CategoriaCrudRepository;
import com.ignaciocassi.marketAPI.persistence.entities.Categoria;
import com.ignaciocassi.marketAPI.persistence.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

//Repositorio de la capa de persistencia.
//Se encarga realizar las transferencias con la base de datos, utilizando productoCrudRepository (CRUD y C. Personaliz).
//Utiliza mapper para convertir de Entidad de Persistencia (Categoria) que son devueltas por las operaciones de
//ProductoCrudRepository, a Entidad de dominio (Category).

@Repository
public class CategoriaRepository implements CategoryRepository {

    @Autowired
    private CategoriaCrudRepository categoriaCrudRepository;

    @Autowired
    private CategoryMapper mapper;

    @Override
    public Optional<List<Category>> getAll() {
        List<Categoria> categorias = (List<Categoria>)categoriaCrudRepository.findAll();
        return Optional.of(mapper.toCategories(categorias));
    }

    @Override
    public Optional<Category> getCategory(int categoryId) {
        return categoriaCrudRepository.findById(categoryId)
                .map(categoria -> mapper.toCategory(categoria));
    }

    @Override
    public Optional<List<Category>> getCategoryByName(String name) {
        List<Categoria> categorias = categoriaCrudRepository.findByDescripcionContainingIgnoreCase(name);
        return Optional.of(mapper.toCategories(categorias));
    }

    @Override
    public Category save(Category category) {
        Categoria categoria = mapper.toCategoria(category);
        return mapper.toCategory(categoriaCrudRepository.save(categoria));
    }

    @Override
    public void delete(int categoryId) {
        categoriaCrudRepository.deleteById(categoryId);
    }

    @Override
    public void toggleStatus(int categoryId) {
        categoriaCrudRepository.toggleStatus(categoryId);
    }
}
