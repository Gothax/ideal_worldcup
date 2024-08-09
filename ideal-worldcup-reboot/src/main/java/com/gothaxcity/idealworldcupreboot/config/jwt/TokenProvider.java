package com.gothaxcity.idealworldcupreboot.config.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;

import java.util.Date;
import java.util.stream.Collectors;

import static com.gothaxcity.idealworldcupreboot.constant.Constants.ACCESS_TOKEN_EXPIRE_TIME;
import static com.gothaxcity.idealworldcupreboot.constant.Constants.REFRESH_TOKEN_EXPIRE_TIME;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    @Value("${jwt.key}")
    private String key;
    private SecretKey secretKey;

    private static final String KEY_ROLE = "role";
//    private final TokenService tokenService;

    @PostConstruct
    private void setSecretKey() {
        secretKey = Keys.hmacShaKeyFor(key.getBytes());
    }

    private String generateTokenSimpleVer(Authentication authentication, long expireTime) {
        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + expireTime);

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(KEY_ROLE, authorities)
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String generateAccessToken(Authentication authentication) {
        return generateTokenSimpleVer(authentication, ACCESS_TOKEN_EXPIRE_TIME);
    }

//    public String generateAccessToken(Authentication authentication) {
//        return generateToken(authentication, ACCESS_TOKEN_EXPIRE_TIME);
//    }

//    public void generateRefreshToken(Authentication authentication, String accessToken) {
//        String refreshToken = generateToken(authentication, REFRESH_TOKEN_EXPIRE_TIME);
//        tokenService.saveOrUpdate(authentication.getName(), refreshToken, accessToken);
//    }



//    private String generateToken(Authentication authentication, long expireTime) {
//        Date now = new Date();
//        Date expiredDate = new Date(now.getTime() + expireTime);
//
//        String authorities = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining());
//
//        return Jwts.builder()
//                .setSubject(authentication.getName())
//                .claim(KEY_ROLE, authorities)
//                .setIssuedAt(now)
//                .setExpiration(expiredDate)
//                .signWith(secretKey, Jwts.SIG.HS512)
//                .compact();
//    }

//    public Authentication getAuthentication(String token) {
//        Claims claims = parseClaims(token);
//        List<SimpleGrantedAuthority> authorities = getAuthorities(claims);
//
//        User principal = new User(claims.getSubject(), "", authorities);
//        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
//    }
//
//    private List<SimpleGrantedAuthority> getAuthorities(Claims claims) {
//        return Collections.singletonList(new SimpleGrantedAuthority(
//                claims.get(KEY_ROLE).toString()));
//    }
//
//    public String reissueAccessToken(String accessToken) {
//        if (StringUtils.hasText(accessToken)) {
//            Token token = tokenService.findByAccessTokenOrThrow(accessToken);
//            String refreshToken = token.getRefreshToken();
//
//            if (validateToken(refreshToken)) {
//                String reissueAccessToken = generateAccessToken(getAuthentication(refreshToken));
//                tokenService.updateToken(reissueAccessToken, token);
//                return reissueAccessToken;
//            }
//        }
//        return null;
//    }
//
//    public boolean validateToken(String token) {
//        if (!StringUtils.hasText(token)) {
//            return false;
//        }
//
//        Claims claims = parseClaims(token);
//        return claims.getExpiration().after(new Date());
//    }
//
//    private Claims parseClaims(String token) {
//        try {
//            return Jwts.parser().verifyWith(secretKey).build()
//                    .parseSignedClaims(token).getPayload();
//        } catch (ExpiredJwtException e) {
//            return e.getClaims();
//        } catch (MalformedJwtException e) {
//            throw new TokenException(INVALID_TOKEN);
//        } catch (SecurityException e) {
//            throw new TokenException(INVALID_JWT_SIGNATURE);
//        }
//    }
}