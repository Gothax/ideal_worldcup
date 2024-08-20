package com.gothaxcity.idealworldcupreboot.accounts.service;

import com.gothaxcity.idealworldcupreboot.accounts.domain.Role;
import com.gothaxcity.idealworldcupreboot.accounts.domain.UserEntity;
import com.gothaxcity.idealworldcupreboot.accounts.dto.request.JoinRequest;
import com.gothaxcity.idealworldcupreboot.accounts.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinRequest joinRequest) {

        boolean existsByEmail = userRepository.existsByEmail(joinRequest.email());
        if (existsByEmail) {
            return;
        }
        UserEntity user = new UserEntity(bCryptPasswordEncoder.encode(joinRequest.password()), joinRequest.email(), joinRequest.nickname(), Role.USER);
        userRepository.save(user);
    }
}
