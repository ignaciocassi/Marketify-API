package com.ignaciocassi.market.domain.repository;

import com.ignaciocassi.market.persistence.entities.Usuario;

public interface UserRepository {
    Usuario save(Usuario usuario);
}
