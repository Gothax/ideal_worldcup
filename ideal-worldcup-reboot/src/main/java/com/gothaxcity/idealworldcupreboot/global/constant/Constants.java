package com.gothaxcity.idealworldcupreboot.global.constant;

/**
 * 설정 변경은 여기에서 OR applications.yml
 */
public class Constants {

    public static final String[] ALLOWED_URLS = {"/login", "/", "/join", "/auth/success", "/error"};
    public static final String CORS_ALLOW_ORIGIN = "http://localhost:3000";

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24L;
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60L * 24 * 7;
}
