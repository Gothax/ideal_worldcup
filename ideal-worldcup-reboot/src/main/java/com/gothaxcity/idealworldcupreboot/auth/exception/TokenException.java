package com.gothaxcity.idealworldcupreboot.auth.exception;

import com.gothaxcity.idealworldcupreboot.exception.CustomException;
import com.gothaxcity.idealworldcupreboot.exception.ErrorCode;

public class TokenException extends CustomException {
    public TokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}