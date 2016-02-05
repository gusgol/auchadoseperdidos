package com.codeego.auchadoseperdidos.utils;

/**
 * Created by Gustavo on 2/5/16.
 */
public class Utils {

    public static String encodeEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }

    public static String decodeEmail(String userEmail) {
        return userEmail.replace(",", ".");
    }
}
