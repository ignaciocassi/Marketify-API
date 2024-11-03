package com.ignaciocassi.market.persistence.crud;

import com.ignaciocassi.market.persistence.entities.Producto;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

//Provee la funcionalidad CRUD mediante la interface heredada CrudRepository, es utilizada dentro de ProductoRepository.
//Contiene el comportamiento personalizador del respositorio (Query Methods, Native Queries...)

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    List<Producto> findByIdCategoriaOrderByNombreAsc(Integer idCategoria);

    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    List<Producto> findByCantidadStockLessThan(Integer stockmin);

}
