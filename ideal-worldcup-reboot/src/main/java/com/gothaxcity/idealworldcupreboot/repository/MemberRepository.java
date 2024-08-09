package com.gothaxcity.idealworldcupreboot.repository;

import com.gothaxcity.idealworldcupreboot.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
    Member findByEmail(String email);
}
