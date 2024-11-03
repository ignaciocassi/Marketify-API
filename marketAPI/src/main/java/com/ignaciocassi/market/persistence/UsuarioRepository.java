package com.ignaciocassi.market.persistence;

import com.ignaciocassi.market.domain.repository.UserRepository;
import com.ignaciocassi.market.persistence.crud.UsuarioCrudRepository;
import com.ignaciocassi.market.persistence.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioRepository implements UserRepository {

    @Autowired
    UsuarioCrudRepository usuarioCrudRepository;

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioCrudRepository.save(usuario);
    }
}
