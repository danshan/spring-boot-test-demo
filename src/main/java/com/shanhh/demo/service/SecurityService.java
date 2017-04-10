package com.shanhh.demo.service;

import com.shanhh.demo.bean.User;

/**
 * @author dan
 * @since 2017-04-10 15:03
 */
public interface SecurityService {
    User signIn(String email, String password);
    User signUp(String email, String password, String nickname);
}
