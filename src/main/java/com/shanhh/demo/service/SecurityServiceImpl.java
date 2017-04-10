package com.shanhh.demo.service;

import com.shanhh.demo.bean.User;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

/**
 * @author dan
 * @since 2017-04-10 15:03
 */
@Service
@Slf4j
public class SecurityServiceImpl implements SecurityService {

    @Override
    public User signIn(String email, String password) {
        return null;
    }

    @Override
    public User signUp(String email, String password, String nickname) {
        return null;
    }
}
