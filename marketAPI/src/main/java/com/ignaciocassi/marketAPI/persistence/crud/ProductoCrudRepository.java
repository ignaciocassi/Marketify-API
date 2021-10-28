package com.ignaciocassi.marketAPI.persistence.crud;

import com.ignaciocassi.marketAPI.persistence.entities.Producto;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    List<Producto> findByIdCategoriaOrderByNombreAsc(Integer idCategoria);

    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    List<Producto> findByCantidadStockLessThan(Integer stockmin);

}
