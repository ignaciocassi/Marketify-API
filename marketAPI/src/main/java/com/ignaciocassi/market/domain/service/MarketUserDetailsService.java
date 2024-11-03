package com.ignaciocassi.market.domain.service;

import com.ignaciocassi.market.persistence.crud.UsuarioCrudRepository;
import com.ignaciocassi.market.persistence.entities.Usuario;
import com.ignaciocassi.market.web.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MarketUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsCheckerService userDetailsCheckerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioCrudRepository.findByUsername(username);
        usuario.orElseThrow(() -> new UsernameNotFoundException("Error: El nombre de usuario "+username+" no está registrado."));
        return usuario.map(MyUserDetails::new).get();
    }

}
