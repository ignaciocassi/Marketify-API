package com.ignaciocassi.market.domain.service;

import com.ignaciocassi.market.domain.dto.AuthenticationRequest;
import com.ignaciocassi.market.persistence.crud.UsuarioCrudRepository;
import com.ignaciocassi.market.persistence.entities.Usuario;
import com.ignaciocassi.market.web.exceptions.PasswordNotValidException;
import com.ignaciocassi.market.web.exceptions.UsernameExistsException;
import com.ignaciocassi.market.web.exceptions.UsernameNotValidException;
import com.ignaciocassi.market.web.messages.ResponseStrings;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRegistratorService {

    private final UsuarioCrudRepository usuarioCrudRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsCheckerService userDetailsCheckerService;

    public UserRegistratorService(UsuarioCrudRepository usuarioCrudRepository, PasswordEncoder passwordEncoder, UserDetailsCheckerService userDetailsCheckerService) {
        this.usuarioCrudRepository = usuarioCrudRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsCheckerService = userDetailsCheckerService;
    }

    public Usuario registerNewUsuario(AuthenticationRequest authRequest) throws UsernameExistsException, UsernameNotValidException, PasswordNotValidException {
        Optional<Usuario> userRegisteredWithUsername = usuarioCrudRepository.findByUsername(authRequest.getUsername());
        if (userRegisteredWithUsername.isPresent()) {
            throw new UsernameExistsException(ResponseStrings.USERNAME_REGISTERED_TRY_LOGIN);
        } else if (!userDetailsCheckerService.checkUsername(authRequest.getUsername())) {
            throw new UsernameNotValidException(ResponseStrings.USERNAME_UNACCEPTABLE);
        } else if (!userDetailsCheckerService.checkPassword(authRequest.getPassword())) {
            throw new PasswordNotValidException(ResponseStrings.PASSWORD_UNACCEPTABLE);
        } else {
            Usuario nuevoUsuario = new Usuario(authRequest.getUsername(), passwordEncoder.encode(authRequest.getPassword()), true, "ROLE_USER");
            return usuarioCrudRepository.save(nuevoUsuario);
        }
    }

}
