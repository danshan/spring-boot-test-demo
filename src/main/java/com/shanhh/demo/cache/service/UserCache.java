package com.shanhh.demo.cache.service;

import com.shanhh.demo.bean.dto.User;

/**
 * @author dan
 * @since 2017-04-22 07:30
 */
public interface UserCache {

    User loadBySessionId(String sessionId);

    void saveSession(User user, String sessionId);
}
