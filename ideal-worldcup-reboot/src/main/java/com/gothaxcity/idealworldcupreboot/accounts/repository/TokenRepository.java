package com.gothaxcity.idealworldcupreboot.accounts.repository;


import com.gothaxcity.idealworldcupreboot.accounts.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface TokenRepository extends JpaRepository<Token, Long> {
    void deleteByRefreshToken(String refreshToken);
    void deleteByExpiration(Date expiration);
}
