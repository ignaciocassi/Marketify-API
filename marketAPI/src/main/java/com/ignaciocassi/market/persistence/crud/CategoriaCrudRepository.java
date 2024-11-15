package com.ignaciocassi.market.persistence.crud;

import com.ignaciocassi.market.persistence.entities.Categoria;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CategoriaCrudRepository extends CrudRepository<Categoria, Integer> {

    List<Categoria> findByDescripcionContainingIgnoreCase(String descripcion);

    @Modifying
    @Transactional
    @Query(value = "UPDATE categorias SET estado = CASE WHEN estado = true THEN false ELSE true END WHERE id_categoria = :idCategoria", nativeQuery = true)
    void toggleStatus(int idCategoria);

}
