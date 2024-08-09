package com.gothaxcity.idealworldcupreboot.dto;

import jakarta.security.auth.message.AuthException;
import jakarta.security.auth.message.AuthStatus;

import java.util.Map;

public record OAuth2UserInfo( String email, String nickName) {

    public static OAuth2UserInfo of(String registrationId, Map<String, Object> attributes) {
        return switch (registrationId) {
            case "google" -> ofGoogle(attributes);
//            case "naver" -> ofNaver(attributes)
//            case "kakao" -> ofKakao(attributes)
//            case "faceBook" -> ofFaceBook(attributes)
            default -> throw new AuthException(ILLEGAL_REGISTRATION_ID);
        };
    }

    private static OAuth2UserInfo ofGoogle(Map<String, Object> attributes) {
        return new OAuth2UserInfo(attributes.get("email").toString(), attributes.get("nickname").toString());
    }
}

