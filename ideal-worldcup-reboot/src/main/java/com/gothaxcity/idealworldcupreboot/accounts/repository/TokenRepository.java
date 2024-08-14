package com.gothaxcity.idealworldcupreboot.accounts.repository;

import com.gothaxcity.idealworldcupreboot.accounts.domain.Token;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token, String> {
    Optional<Token> findByAccessToken(String accessToken);
}
