package com.gothaxcity.idealworldcupreboot.dto.response;

import jakarta.validation.constraints.NotBlank;

public class LoginResponse {

    @NotBlank
    private String accessToken;
}
