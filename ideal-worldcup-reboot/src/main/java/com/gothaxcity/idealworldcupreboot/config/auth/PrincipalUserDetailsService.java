package com.gothaxcity.idealworldcupreboot.config.auth;

import com.gothaxcity.idealworldcupreboot.domain.Member;
import com.gothaxcity.idealworldcupreboot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserName(username);

        return new PrincipalUserDetails(member);
    }
}
