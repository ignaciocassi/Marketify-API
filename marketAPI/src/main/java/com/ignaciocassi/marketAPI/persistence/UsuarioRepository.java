package com.ignaciocassi.marketAPI.persistence;

import com.ignaciocassi.marketAPI.domain.repository.UserRepository;
import com.ignaciocassi.marketAPI.persistence.crud.UsuarioCrudRepository;
import com.ignaciocassi.marketAPI.persistence.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioRepository implements UserRepository {

    @Autowired
    UsuarioCrudRepository usuarioCrudRepository;

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioCrudRepository.save(usuario);
    }
}
