package com.github.son_daehyeon.template.domain.auth.repository;

import com.github.son_daehyeon.template.common.redis.RedisRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RefreshTokenRepository extends RedisRepository {

    public RefreshTokenRepository(RedisTemplate<String, String> redisTemplate) {

        super(redisTemplate, "token");
    }
}
