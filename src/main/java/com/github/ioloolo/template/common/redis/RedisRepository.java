package com.github.ioloolo.template.common.redis;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public abstract class RedisRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private final String group;

    protected RedisRepository(RedisTemplate<String, String> redisTemplate, String group) {
        this.redisTemplate = redisTemplate;
        this.group = group;
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(getKey(key), value.toString());
    }

    public void setTtl(String key, Object value, long ttl, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(getKey(key), value.toString(), ttl, timeUnit);
    }

    public Optional<String> get(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(getKey(key)));
    }

    public void delete(String key) {
        redisTemplate.opsForValue().getAndDelete(getKey(key));
    }

    private String getKey(String key) {
        return group + ":" + key;
    }
}
