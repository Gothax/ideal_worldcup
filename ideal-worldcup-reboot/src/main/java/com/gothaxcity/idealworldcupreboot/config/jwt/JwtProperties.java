package com.gothaxcity.idealworldcupreboot.config.jwt;

import static com.gothaxcity.idealworldcupreboot.constant.Constants.ACCESS_TOKEN_EXPIRE_TIME;

public interface JwtProperties {
    String SECRET = "1csdweqEASNMlkjsdq412zxawe";
    Long EXPIRATION_TIME = ACCESS_TOKEN_EXPIRE_TIME;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
