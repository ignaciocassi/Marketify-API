package com.ignaciocassi.market.persistence.crud;

import com.ignaciocassi.market.persistence.entities.Cliente;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface ClienteCrudRepository extends CrudRepository<Cliente,Integer> {
    Optional<Cliente> findById(String id);
}
