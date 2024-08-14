package com.gothaxcity.idealworldcupreboot.accounts.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.*;

@Component
public class JwtTokenProvider {

    private final SecretKey secretKey;

    public JwtTokenProvider(@Value("${jwt.key}") String key) {
        secretKey = new SecretKeySpec(key.getBytes(UTF_8), Jwts.SIG.HS512.key().build().getAlgorithm());
    }

    public String generateToken(Authentication authentication, Long expirationMs) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .compact();
    }

}
