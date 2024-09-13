package com.gothaxcity.idealworldcupreboot.accounts.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader("access");
        String refreshToken = request.getHeader("refresh");

        if (accessToken == null || accessToken.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }


        Boolean isValidAccessToken = jwtTokenProvider.validateToken(accessToken);
        if (isValidAccessToken) {
            try {
                // Access Token이 만료되었는지 검사, context에 저장
                jwtTokenProvider.isTokenExpired(accessToken);
                SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.getAuthentication(accessToken));
            } catch (ExpiredJwtException e) {
                // Access Token이 만료된 경우 처리
                if (jwtTokenProvider.validateToken(refreshToken)) {
                    // 새로운 Access Token 발급
                    String newAccessToken = jwtTokenProvider.generateAccessToken(jwtTokenProvider.getAuthentication(refreshToken));

                    response.setHeader("access", newAccessToken);
                    SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.getAuthentication(newAccessToken));
                } else {
                    // Refresh Token이 유효하지 않음
                    // 필요하다면 예외를 던지거나, 다른 처리 로직을 추가
                    throw new SecurityException("Refresh token is invalid or expired.");
                }
            }
        } else {
            // Access Token이 유효하지 않음
            throw new SecurityException("Access token is invalid.");
        }

        filterChain.doFilter(request, response);
    }
}
