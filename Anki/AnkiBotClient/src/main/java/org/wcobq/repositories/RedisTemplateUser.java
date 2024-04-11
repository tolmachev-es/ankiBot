package org.wcobq.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisTemplateUser {
    private final RedisTemplate<String, String> redisTemplate;

    public void save(String userId, String parameter) {
        redisTemplate.opsForValue().set(userId, parameter);
    }

    public String get(String userId) {
        return redisTemplate.opsForValue().get(userId);
    }

    public void delete(String userId) {
        redisTemplate.delete(userId);
    }
}
