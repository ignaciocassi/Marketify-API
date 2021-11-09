package com.ignaciocassi.marketAPI.domain.repository;

import com.ignaciocassi.marketAPI.persistence.entities.Usuario;

public interface UserRepository {
    Usuario save(Usuario usuario);
}
