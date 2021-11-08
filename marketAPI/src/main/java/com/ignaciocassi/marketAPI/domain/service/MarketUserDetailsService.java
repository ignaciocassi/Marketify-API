package com.ignaciocassi.marketAPI.domain.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.ignaciocassi.marketAPI.domain.dto.AuthenticationRequest;
import com.ignaciocassi.marketAPI.persistence.crud.UsuarioCrudRepository;
import com.ignaciocassi.marketAPI.persistence.entities.Usuario;
import com.ignaciocassi.marketAPI.web.exceptions.PasswordNotValidException;
import com.ignaciocassi.marketAPI.web.exceptions.UsernameExistsException;
import com.ignaciocassi.marketAPI.web.exceptions.UsernameNotValidException;
import com.ignaciocassi.marketAPI.web.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
