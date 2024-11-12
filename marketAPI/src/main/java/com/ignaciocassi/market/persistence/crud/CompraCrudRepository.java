package com.ignaciocassi.market.persistence.crud;

import com.ignaciocassi.market.persistence.entities.Compra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {

    //Query methods.
    Optional<List<Compra>> findByIdCliente(String idCliente);

    @Query(value = "SELECT * FROM compras c JOIN compras_productos cp " +
            "ON c.id_compra = cp.id_compra WHERE cp.id_producto = ?1", nativeQuery = true)
    Optional<List<Compra>> findByContainingProduct(int productId);

}
