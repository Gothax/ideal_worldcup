package com.gothaxcity.idealworldcupreboot.config.jwt;

import com.gothaxcity.idealworldcupreboot.repository.MemberRepository;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

import static com.gothaxcity.idealworldcupreboot.config.jwt.JwtProperties.HEADER_STRING;
import static com.gothaxcity.idealworldcupreboot.config.jwt.JwtProperties.TOKEN_PREFIX;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final MemberRepository memberRepository;
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        String token = request.getHeader(HEADER_STRING)
                .replace(TOKEN_PREFIX, "");



    }
}
