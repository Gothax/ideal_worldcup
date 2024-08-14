package com.gothaxcity.idealworldcupreboot.accounts.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.gothaxcity.idealworldcupreboot.global.constant.Constants.CORS_ALLOW_ORIGIN;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(CORS_ALLOW_ORIGIN);
    }

}
