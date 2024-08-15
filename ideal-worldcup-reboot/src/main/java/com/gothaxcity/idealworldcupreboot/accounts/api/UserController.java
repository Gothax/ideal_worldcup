package com.gothaxcity.idealworldcupreboot.accounts.api;

import com.gothaxcity.idealworldcupreboot.accounts.dto.request.JoinRequest;
import com.gothaxcity.idealworldcupreboot.accounts.dto.response.JoinResponse;
import com.gothaxcity.idealworldcupreboot.accounts.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<JoinResponse> join(JoinRequest joinRequest) {
        userService.joinProcess(joinRequest);
        return ResponseEntity.ok().build();
    }
}
