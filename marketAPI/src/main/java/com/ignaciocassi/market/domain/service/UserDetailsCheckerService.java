package com.ignaciocassi.market.domain.service;

import org.springframework.stereotype.Service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserDetailsCheckerService {

    public boolean checkUsername(String username) {
        final String usernamePattern = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){8,18}[a-zA-Z0-9]$";
        Pattern pattern = Pattern.compile(usernamePattern);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public boolean checkPassword(String password) {
        final String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
