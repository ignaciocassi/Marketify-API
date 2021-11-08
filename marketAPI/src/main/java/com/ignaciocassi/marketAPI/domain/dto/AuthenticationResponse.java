package com.ignaciocassi.marketAPI.domain.dto;

//Utilizada para enviar la respuesta de una autenticación (JWT) mediante el controlador AuthController.
public class AuthenticationResponse {


    private String message;
    private String jwt;

    public AuthenticationResponse(String message) {
        this.message = message;
    }
    public AuthenticationResponse(String message, String jwt) {
        this.message = message;
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
