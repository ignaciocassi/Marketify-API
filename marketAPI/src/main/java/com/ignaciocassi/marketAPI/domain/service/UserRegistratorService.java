package com.ignaciocassi.marketAPI.domain.service;

import com.ignaciocassi.marketAPI.domain.dto.AuthenticationRequest;
import com.ignaciocassi.marketAPI.persistence.crud.UsuarioCrudRepository;
import com.ignaciocassi.marketAPI.persistence.entities.Usuario;
import com.ignaciocassi.marketAPI.web.exceptions.PasswordNotValidException;
import com.ignaciocassi.marketAPI.web.exceptions.UsernameExistsException;
import com.ignaciocassi.marketAPI.web.exceptions.UsernameNotValidException;
import com.ignaciocassi.marketAPI.web.messages.ResponseStrings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRegistratorService {

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsCheckerService userDetailsCheckerService;

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
