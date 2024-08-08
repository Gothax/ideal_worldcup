package com.gothaxcity.idealworldcupreboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // jwt 인증 -> csrf 비활성화
        httpSecurity
                .formLogin((auth)->auth.formLogin().disable())
                .httpBasic((auth)->httpBaisc().disable())
                .csrf((auth)->csrf.disable());

        httpSecurity
                .authorizationRequest(
                        (auth)->auth
                                .requestMatchers("/login","/","/join")).permitAll()
                                .requestMatchers("/admin").hasAnyRole("ADMIN")
                                .anyRequest().authenticated();
		        )

        // jwt에서는 세션을 생성할 필요 없다(stateless)
        httpSecurity
                .sessionManagement((session)->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();

    }

}
