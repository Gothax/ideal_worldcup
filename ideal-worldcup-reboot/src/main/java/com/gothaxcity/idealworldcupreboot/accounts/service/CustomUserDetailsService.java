package com.gothaxcity.idealworldcupreboot.accounts.service;

import com.gothaxcity.idealworldcupreboot.accounts.domain.UserEntity;
import com.gothaxcity.idealworldcupreboot.accounts.dto.PrincipalUserDetails;
import com.gothaxcity.idealworldcupreboot.accounts.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public PrincipalUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new PrincipalUserDetails(user);
    }
}
