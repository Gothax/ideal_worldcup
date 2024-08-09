package com.gothaxcity.idealworldcupreboot.exception;

public class AuthException extends CustomException{
    public AuthException(ErrorCode errorCode) {
        super(errorCode);
    }
}
