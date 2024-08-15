package com.gothaxcity.idealworldcupreboot.accounts.jwt;

import com.gothaxcity.idealworldcupreboot.accounts.domain.UserEntity;
import com.gothaxcity.idealworldcupreboot.accounts.dto.PrincipalUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.gothaxcity.idealworldcupreboot.global.constant.Constants.ACCESS_TOKEN_EXPIRE_TIME;
import static com.gothaxcity.idealworldcupreboot.global.constant.Constants.REFRESH_TOKEN_EXPIRE_TIME;
import static java.nio.charset.StandardCharsets.*;

@Component
public class JwtTokenProvider {

    private final SecretKey secretKey;

    public JwtTokenProvider(@Value("${jwt.key}") String key) {
        secretKey = new SecretKeySpec(key.getBytes(UTF_8), Jwts.SIG.HS512.key().build().getAlgorithm());
    }

    public String generateAccessToken(Authentication authentication) {
        return generateToken(authentication, ACCESS_TOKEN_EXPIRE_TIME, "access");
    }

    public String generateRefreshToken(Authentication authentication) {
        return generateToken(authentication, REFRESH_TOKEN_EXPIRE_TIME, "refresh");
    }

    private String generateToken(Authentication authentication, Long expirationMs, String category) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());

        return Jwts.builder()
                .subject(authentication.getName())
                .claim("category", category)
                .claim("authorities", authorities)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(secretKey)
                .compact();
    }
//
//    public Authentication getAuthentication(String token) {
//        UserEntity user = new UserEntity("tempPassword", getTokenSubject(token), );
//        PrincipalUserDetails principal = new PrincipalUserDetails(user);
//        return new UsernamePasswordAuthenticationToken(principal, token, principal.getAuthorities());
//    }

    public String getTokenSubject(String token) {
        return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token).getPayload().getSubject();
    }

    public String getCategory(String token) {
        return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token).getPayload().get("category", String.class);
    }

    public String getAuthorities(String token) {
        return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token).getPayload().get("authorities", String.class);
    }

    public Boolean isTokenExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }





}
