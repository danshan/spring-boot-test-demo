package com.shanhh.demo.service;

import com.shanhh.demo.bean.dto.User;

/**
 * @author dan
 * @since 2017-04-10 15:03
 */
public interface SecurityService {
    String signIn(String email, String password);
    String signUp(String email, String password, String nickname);
    User fetchUser(String sessionId);
}
