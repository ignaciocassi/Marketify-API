package com.ignaciocassi.market.persistence.crud;

import com.ignaciocassi.market.persistence.entities.Producto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

//Provee la funcionalidad CRUD mediante la interface heredada CrudRepository, es utilizada dentro de ProductoRepository.
//Contiene el comportamiento personalizador del respositorio (Query Methods, Native Queries...)

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    List<Producto> findByIdCategoriaOrderByNombreAsc(Integer idCategoria);

    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    List<Producto> findByCantidadStockLessThan(Integer stockmin);

    Optional<Producto> findByNombreEquals(String nombre);

    @Modifying
    @Transactional
    @Query("UPDATE Producto SET estado = false WHERE idProducto = :idProducto")
    Integer softDeleteByIdProducto(@Param("idProducto") Integer idProducto);

}
