package com.ignaciocassi.marketAPI.persistence.crud;

import com.ignaciocassi.marketAPI.persistence.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UsuarioCrudRepository extends CrudRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);
}
