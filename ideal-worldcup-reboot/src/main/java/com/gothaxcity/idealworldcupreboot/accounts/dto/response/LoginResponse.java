package com.gothaxcity.idealworldcupreboot.accounts.dto.response;

import jakarta.validation.constraints.NotBlank;

public class LoginResponse {

    @NotBlank
    private String accessToken;
}
