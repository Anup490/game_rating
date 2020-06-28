package com.anup.gamedomain.core;

import com.anup.gamedomain.api.GameResponse;

public class GameResponseImpl implements GameResponse {

    private int code;
    private String message;
    private Object data;

    public GameResponseImpl(int code, String message, Object data){
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
