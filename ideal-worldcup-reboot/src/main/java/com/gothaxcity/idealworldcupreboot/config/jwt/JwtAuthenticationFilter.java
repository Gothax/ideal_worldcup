package com.gothaxcity.idealworldcupreboot.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gothaxcity.idealworldcupreboot.config.auth.PrincipalUserDetails;
import com.gothaxcity.idealworldcupreboot.dto.request.LoginRequest;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

import static com.gothaxcity.idealworldcupreboot.config.jwt.JwtProperties.HEADER_STRING;
import static com.gothaxcity.idealworldcupreboot.config.jwt.JwtProperties.TOKEN_PREFIX;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider provider;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();
        LoginRequest loginRequest = null;
        try {
            loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 토큰 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUserName()
                , loginRequest.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        PrincipalUserDetails principalDetails = (PrincipalUserDetails) authResult.getPrincipal();

        String jwtToken = provider.generateAccessToken(authResult);
        response.addHeader(HEADER_STRING, TOKEN_PREFIX+jwtToken);
    }
}
