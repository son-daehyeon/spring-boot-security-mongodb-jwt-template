package com.github.ioloolo.template.domain.user.repository;

import com.github.ioloolo.template.common.redis.RedisRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RefreshTokenRepository extends RedisRepository {

    public RefreshTokenRepository(RedisTemplate<String, String> redisTemplate) {

        super(redisTemplate, "token");
    }
}
