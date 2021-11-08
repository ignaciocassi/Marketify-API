package com.ignaciocassi.marketAPI.web.messages;

import org.springframework.boot.ansi.Ansi8BitColor;

public class ResponseStrings {

    public static final String CLIENT_NOT_FOUND = "No se encontró ningún cliente con esa ID.";
    public static final String NO_PURCHASES_MADE = "El cliente aún no ha realizado ninguna compra.";
    public static final String NO_PURCHASES_LISTED = "Aún no se han registrado compras.";
    public static final String NO_CATEGORIES_FOUND = "No existen categorías que coincidan con la búsqueda solicitada.";
    public static final String CATEGORY_NOT_FOUND = "No se ha encontrado la categoría.";
    public static final String NO_CATEGORIES_LISTED = "Aún no se han creado categorías.";
    public static final String NO_PRODUCTS_FOUND = "No existen productos que coincidan con la búsqueda solicitada.";
    public static final String NO_SCARSE_PRODUCTS = "No existen productos escasos con un stock menor al solicitado.";
    public static final String NO_PRODUCTS_IN_CATEGORY = "Esta categoría aún no tiene productos o no existe.";
    public static final String PRODUCT_NOT_FOUND = "Producto no encontrado.";
    public static final String NO_PRODUCTS_LISTED = "Aún no se han creado productos.";
    public static final String USERNAME_PASSWORD_INCORRECT = "El nombre de usuario o contraseña no son correctos, reintente.";
    public static final String SUCCESSFULL_REGISTRATION = "El usuario ha sido registrado exitosamente.";
    public static final String USERNAME_TAKEN = "El nombre de usuario ya está registrado, por favor elija otro.";
    public static final String USERNAME_UNACCEPTABLE = "El nombre de usuario no es válido. Debe comenzar con una letra, tener un largo entre 10 y 20 caracteres y puede contener números.";
    public static final String PASSWORD_UNACCEPTABLE = "La contraseña no es válida. Debe tener al menos 8 caracteres, una letra mayúscula, una letra minúscula, un número y un caracter especial (!,@,#,&).";
    public static final String USERNAME_REGISTERED_TRY_LOGIN = "El nombre de usuario ingresado ya se encuentra registrado, intente hacer login.";

}
