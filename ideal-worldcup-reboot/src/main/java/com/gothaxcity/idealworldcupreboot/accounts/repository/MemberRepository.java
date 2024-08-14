package com.gothaxcity.idealworldcupreboot.accounts.repository;

import com.gothaxcity.idealworldcupreboot.accounts.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);
}
