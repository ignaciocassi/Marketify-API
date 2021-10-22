package com.ignaciocassi.marketAPI.domain.repository;

import com.ignaciocassi.marketAPI.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    //Define las reglas que tiene el dominio al momento de que cualquier repositorio,
    //quiere utilizar o acceder a produtos en la base datos (entities en la capa de persistencia).

    List<Product> getAll();

    Optional<List<Product>> getByCategory(int categoryId);

    Optional<List<Product>> getScarceProducts(int quantity);

    Optional<Product> getProduct(int productId);

    Optional<Product> getProductByName(String name);

    Product save(Product product);

    void delete(int productId);

    void delete(String name);

}
