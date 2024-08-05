package com.gothaxcity.idealworldcupreboot.service;

import com.gothaxcity.idealworldcupreboot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
}
