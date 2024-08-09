package com.gothaxcity.idealworldcupreboot.constant;

/**
 * 설정 변경은 여기에서 OR applications.yml
 */
public class Constants {

    public static final String[] ALLOWED_URLS = {"/login", "/", "/join"};

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30L;
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60L * 24 * 7;
}
