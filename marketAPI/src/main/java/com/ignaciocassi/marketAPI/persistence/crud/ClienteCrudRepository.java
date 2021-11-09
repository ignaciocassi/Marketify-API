package com.ignaciocassi.marketAPI.persistence.crud;

import com.ignaciocassi.marketAPI.persistence.entities.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClienteCrudRepository extends CrudRepository<Cliente,Integer> {
    Optional<Cliente> findById(String id);
}
