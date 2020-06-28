package com.anup.gamedomain.enums;

public enum ValidationStatus {
    NO_NAME(202,"Name is blank"),
    NO_DESCRIPTION(202,"Description is blank"),
    NO_RATING(202,"Rating is blank"),
    NO_PHOTO(202,"No photo present"),
    OK(200,"Success")
    ;
    private int code;
    private String message;

    ValidationStatus(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
