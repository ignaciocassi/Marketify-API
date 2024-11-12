package com.ignaciocassi.market.domain.repository;

import com.ignaciocassi.market.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    //Especificación del repositorio.
    //Define las operaciones que serán utilizadas en la capa de dominio y que deben ser implementadas
    //Por el respositorio.

    Optional<List<Product>> getAll();

    Optional<List<Product>> getByCategory(int categoryId);

    Optional<List<Product>> getScarce(int quantity);

    Optional<Product> getProduct(int productId);

    Optional<List<Product>> getProductsByNameContaining(String name);

    Optional<Product> getProductByName(String name);

    Product save(Product product);

    Integer deleteByIdProducto(int productId);

}
