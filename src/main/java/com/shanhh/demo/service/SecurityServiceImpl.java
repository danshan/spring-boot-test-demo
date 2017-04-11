package com.shanhh.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shanhh.demo.bean.User;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

import javax.annotation.Resource;

/**
 * @author dan
 * @since 2017-04-10 15:03
 */
@Service
@Slf4j
public class SecurityServiceImpl implements SecurityService {

    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public User signIn(String email, String password) {
        return null;
    }

    @Override
    public User signUp(String email, String password, String nickname) {
        return null;
    }

    @Override
    public User fetchUser(final String sessionId) {
        User user;
        try {
            String userJson = stringRedisTemplate.opsForValue().get("demo:sessionid:" + sessionId);
            if (userJson == null) {
                return null;
            }
            try {
                return objectMapper.readValue(userJson, User.class);
            } catch (IOException e) {
                log.error("parse user obj failed", e);
                return null;
            }
        } catch (Exception e) {
            log.error("fetch user in redis failed", e);
            user = null;
        }

        if (user != null) {
            return user;
        }
        return user;
    }
}
