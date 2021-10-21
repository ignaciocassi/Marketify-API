package com.ignaciocassi.marketAPI.persistence.crud;

import com.ignaciocassi.marketAPI.persistence.entities.Producto;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    //Permite realizar consultas personalizadas, además de los métodos CRUD que hereda de CrudRepository
    //Query methods permiten realizar consultas sin SQL.
    //Siguen estándares de nombramiento.

    List<Producto> findByIdCategoriaOrderByNombreAsc(Integer idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

    Optional<Producto> findByNombre(String nombre);

    void deleteByNombre(String nombre);

    //También pueden usarse Querys nativos, usando SQL.
    //@Query(value = "SELECT * FROM productos WHERE id_categoria = ?",nativeQuery = true)
    //List<Producto> getByCategoria(Integer idCategoria);

}
