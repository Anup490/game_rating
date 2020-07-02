package com.anup.gamewebservice.dto;

import com.anup.gamecore.dto.GameResponse;

public class Response {

    private int code;
    private String message;
    private Object data;

    private Response(){}

    public Response(GameResponse gameResponse){
        this.code = gameResponse.getCode();
        this.message = gameResponse.getMessage();
        this.data = gameResponse.getData();
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
