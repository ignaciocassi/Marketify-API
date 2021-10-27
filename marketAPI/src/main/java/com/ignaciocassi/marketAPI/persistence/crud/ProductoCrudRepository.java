package com.ignaciocassi.marketAPI.persistence.crud;

import com.ignaciocassi.marketAPI.persistence.entities.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    //Permite realizar consultas personalizadas, además de los métodos CRUD que hereda de CrudRepository
    //Query methods permiten realizar consultas sin SQL.
    //Siguen estándares de nombramiento.

    List<Producto> findByIdCategoriaOrderByNombreAsc(Integer idCategoria);

    Optional<Producto> findByNombre(String nombre);

    void deleteByNombre(String nombre);

    @Query(value = "SELECT * FROM productos WHERE cantidad_stock < :quantity AND estado = :activo", nativeQuery = true)
    List<Producto> getScarce(Integer quantity, Boolean activo);

}
