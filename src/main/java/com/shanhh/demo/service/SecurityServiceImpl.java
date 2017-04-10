package com.shanhh.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shanhh.demo.bean.User;
import com.shanhh.demo.jedis.JedisService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPool;

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
    private JedisService jedisService;
    @Resource
    private JedisPool jedisPool;
    @Resource
    private ObjectMapper objectMapper;

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
        User user = jedisService.doJedis(jedis -> {
            String userJson = jedis.get("demo:sessionid:" + sessionId);
            if (userJson == null) {
                return null;
            }
            try {
                return objectMapper.readValue(userJson, User.class);
            } catch (IOException e) {
                log.error("parse user obj failed", e);
                return null;
            }
        }, jedisPool);
        return user;
    }
}
