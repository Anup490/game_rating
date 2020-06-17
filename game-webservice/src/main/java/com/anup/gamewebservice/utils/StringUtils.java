package com.anup.gamewebservice.utils;

public class StringUtils {

    public static boolean isNotBlank(String string){
        return !isBlank(string);
    }

    public static boolean isBlank(String string){
        return (string == null) || string.trim().equalsIgnoreCase("");
    }

}
