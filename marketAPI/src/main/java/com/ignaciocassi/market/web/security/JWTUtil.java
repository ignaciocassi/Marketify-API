package com.ignaciocassi.market.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JWTUtil {

    private static final String KEY = "m4rk3t";

    //Permite generar un JWT a partir de un UserDetails.
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

    //Verifica si un token es válido para un usuario.
    public boolean validateToken(String token, UserDetails userDetails) {
        return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);
    }

    //Obtiene el username del token.
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    //Verifica si el token está expirado.
    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }


    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }
}
