package com.ignaciocassi.market.web.controller;

import com.ignaciocassi.market.domain.dto.AuthenticationRequest;
import com.ignaciocassi.market.domain.dto.AuthenticationResponse;
import com.ignaciocassi.market.domain.service.UserRegistratorService;
import com.ignaciocassi.market.domain.service.UserSigninService;
import com.ignaciocassi.market.web.exceptions.PasswordNotValidException;
import com.ignaciocassi.market.web.exceptions.UsernameExistsException;
import com.ignaciocassi.market.web.exceptions.UsernameNotValidException;
import com.ignaciocassi.market.web.messages.ResponseStrings;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserSigninService userSigninService;

    private final UserRegistratorService userRegistratorService;

    public AuthController(UserSigninService userSigninService, UserRegistratorService userRegistratorService) {
        this.userSigninService = userSigninService;
        this.userRegistratorService = userRegistratorService;
    }

    @PostMapping("/login")
    @ApiOperation("Login to account providing username and password, and get JWT when authenticated.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User successfully logged in."),
            @ApiResponse(code = 403, message = "User login failed.")
    })
    public ResponseEntity<AuthenticationResponse> login(@ApiParam(value = "Body containing username and password in JSON format.") @RequestBody AuthenticationRequest request) {
        try {
            String jwt = userSigninService.loginUsuario(request);
            return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(ResponseStrings.USERNAME_PASSWORD_INCORRECT);
        }
    }

    @PostMapping("/register")
    @ApiOperation("Register new account providing desired username and password.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "User successfully registered."),
            @ApiResponse(code = 409, message = "Username is taken."),
            @ApiResponse(code = 406, message = "Username not valid.")
    })
    public ResponseEntity<String> register(@ApiParam(value = "Body containing username and password in JSON format.")@RequestBody AuthenticationRequest request) {
        try {
            userRegistratorService.registerNewUsuario(request);
            return new ResponseEntity<>(ResponseStrings.SUCCESSFULL_REGISTRATION, HttpStatus.CREATED);
        } catch (UsernameExistsException e) {
            throw new UsernameExistsException(ResponseStrings.USERNAME_TAKEN);
        } catch (UsernameNotValidException e) {
            throw new UsernameNotValidException(ResponseStrings.USERNAME_UNACCEPTABLE);
        } catch (PasswordNotValidException e) {
            throw new PasswordNotValidException(ResponseStrings.PASSWORD_UNACCEPTABLE);
        }
    }

}
