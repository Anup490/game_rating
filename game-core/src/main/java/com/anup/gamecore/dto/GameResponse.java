package com.anup.gamecore.dto;

public class GameResponse {

    private int code;
    private String message;
    private Object data;

    public GameResponse(int code, String message, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

}