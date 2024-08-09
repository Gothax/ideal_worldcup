package com.gothaxcity.idealworldcupreboot.redis;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token, String> {
    Optional<Token> findByAccessToken(String accessToken);
}
