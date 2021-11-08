package com.ignaciocassi.marketAPI.web.messages;

import org.springframework.boot.ansi.Ansi8BitColor;

public class ResponseStrings {

    public static final String SUCCESSFULL_LOGIN = "Bienvenido!";
    public static final String USERNAME_PASSWORD_INCORRECT = "El nombre de usuario o contraseña no son correctos, reintente.";
    public static final String SUCCESSFULL_REGISTRATION = "El usuario ha sido registrado exitosamente.";
    public static final String USERNAME_TAKEN = "El nombre de usuario ya está registrado, por favor elija otro.";
    public static final String USERNAME_UNACCEPTABLE = "El nombre de usuario no es válido.\nDebe comenzar con una letra,\nDebe tener un largo entre 10 y 20 caracteres,\nPuede contener números.";
    public static final String PASSWORD_UNACCEPTABLE = "La contraseña no es válida.\nDebe tener al menos 8 caracteres,\nuna letra mayúscula, una letra minúscula,\nun número y un caracter especial (!,@,#,&).";
    public static final String USERNAME_REGISTERED_TRY_LOGIN = "El nombre de usuario ingresado ya se encuentra registrado, intente hacer login.";

}
