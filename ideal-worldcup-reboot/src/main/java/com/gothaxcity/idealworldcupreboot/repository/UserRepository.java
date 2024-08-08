package com.gothaxcity.idealworldcupreboot.repository;

import com.gothaxcity.idealworldcupreboot.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    UserEntity findByEmail(String email);
}
