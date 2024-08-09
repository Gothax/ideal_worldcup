package com.gothaxcity.idealworldcupreboot.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler {

    private final TokenProvider tokenProvider;
}
