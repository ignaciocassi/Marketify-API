package com.ignaciocassi.marketAPI.domain.dto;

//Utilizada para enviar la respuesta de una autenticación (JWT) mediante el controlador AuthController.
public class AuthenticationResponse {

    private String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

}
