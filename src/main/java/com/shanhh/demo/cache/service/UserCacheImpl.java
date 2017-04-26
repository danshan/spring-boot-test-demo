package com.shanhh.demo.cache.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shanhh.demo.bean.dto.User;
import com.shanhh.demo.cache.key.SessionKey;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

/**
 * @author dan
 * @since 2017-04-22 07:31
 */
@Service
@Slf4j
public class UserCacheImpl implements UserCache {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public User loadBySessionId(String sessionId) {
        String cacheKey = SessionKey.builder()
                .sessionId(sessionId)
                .build().buildKey();
        String userJson = stringRedisTemplate.opsForValue().get(cacheKey);
        if (userJson == null) {
            return null;
        }
        try {
            return objectMapper.readValue(userJson, User.class);
        } catch (IOException e) {
            log.error("parse user obj failed", e);
            return null;
        }
    }

    @Override
    public void saveSession(User user, String sessionId) {
        String cacheKey = SessionKey.builder()
                .sessionId(sessionId)
                .build().buildKey();
        try {
            stringRedisTemplate.opsForValue().set(cacheKey, objectMapper.writeValueAsString(user), 1, TimeUnit.DAYS);
        } catch (JsonProcessingException e) {
            log.error("parse user obj failed", e);
        }

    }
}
