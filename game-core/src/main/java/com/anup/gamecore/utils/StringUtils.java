package com.anup.gamecore.utils;

public class StringUtils {

    public static boolean isNotBlank(String string){
        return !isBlank(string);
    }

    public static boolean isBlank(String string){
        return (string == null) || string.trim().equalsIgnoreCase("");
    }

    public static int toInteger(String string){
        try{
            return Integer.parseInt(string);
        }catch (NumberFormatException e){
            return -1;
        }
    }

}
