package com.ignaciocassi.marketAPI.persistence.crud;

import com.ignaciocassi.marketAPI.persistence.entities.Compra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompraCrudRepository extends CrudRepository<Compra,Integer> {

    //Query methods.
    Optional<List<Compra>> findByIdCliente(String idCliente);

}
