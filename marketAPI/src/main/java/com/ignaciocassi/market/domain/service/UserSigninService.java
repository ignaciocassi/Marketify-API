package com.ignaciocassi.market.domain.service;

import com.ignaciocassi.market.domain.dto.AuthenticationRequest;
import com.ignaciocassi.market.web.security.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserSigninService {

    private final MarketUserDetailsService marketUserDetailsService;

    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;

    public UserSigninService(MarketUserDetailsService marketUserDetailsService, AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.marketUserDetailsService = marketUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public String loginUsuario(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails userDetails = marketUserDetailsService.loadUserByUsername(request.getUsername());
        return jwtUtil.generateToken(userDetails);
    }
}
