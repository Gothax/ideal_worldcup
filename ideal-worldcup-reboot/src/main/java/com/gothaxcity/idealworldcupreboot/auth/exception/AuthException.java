package com.gothaxcity.idealworldcupreboot.auth.exception;

import com.gothaxcity.idealworldcupreboot.exception.CustomException;
import com.gothaxcity.idealworldcupreboot.exception.ErrorCode;

public class AuthException extends CustomException {
    public AuthException(ErrorCode errorCode) {
        super(errorCode);
    }
}
