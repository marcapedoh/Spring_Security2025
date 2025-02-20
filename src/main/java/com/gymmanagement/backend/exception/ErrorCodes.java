package com.gymmanagement.backend.exception;

public enum ErrorCodes {
    USER_NOT_FOUND(1000),
    ROLE_NOT_FOUND(2000),
    ROLE_NOT_VALID(2001),
    USER_NOT_VALID(1001);


    ErrorCodes(int code){
        this.errorCode=code;
    }
    private int errorCode;

    public int getErrorCode(){
        return errorCode;
    }
}
