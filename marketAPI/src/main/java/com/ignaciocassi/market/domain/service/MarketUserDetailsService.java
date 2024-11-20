package com.ignaciocassi.market.domain.service;

import com.ignaciocassi.market.persistence.crud.UsuarioCrudRepository;
import com.ignaciocassi.market.persistence.entities.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class MarketUserDetailsService implements UserDetailsService {

    private UsuarioCrudRepository usuarioCrudRepository;

    public MarketUserDetailsService(UsuarioCrudRepository usuarioCrudRepository) {
        this.usuarioCrudRepository = usuarioCrudRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioCrudRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isActive(),
                true,
                true,
                true,
                new ArrayList<>());
    }

}
