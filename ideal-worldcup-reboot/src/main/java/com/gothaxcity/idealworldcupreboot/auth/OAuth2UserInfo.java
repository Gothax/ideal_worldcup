package com.gothaxcity.idealworldcupreboot.auth;

import com.gothaxcity.idealworldcupreboot.auth.exception.AuthException;
import com.gothaxcity.idealworldcupreboot.domain.Member;

import java.util.Map;

import static com.gothaxcity.idealworldcupreboot.exception.ErrorCode.ILLEGAL_REGISTRATION_ID;
import static com.gothaxcity.idealworldcupreboot.utils.KeyGenerator.*;

public record OAuth2UserInfo( String email, String nickName, String profileImage) {

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
        return new OAuth2UserInfo(attributes.get("email").toString(), attributes.get("nickname").toString(), attributes.get("profileImage").toString());
    }

    public Member toEntity() {
        return new Member(email, nickName, profileImage, generateKey());
    }
}

