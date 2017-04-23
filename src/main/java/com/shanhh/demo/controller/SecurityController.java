package com.shanhh.demo.controller;

import com.shanhh.demo.bean.dto.User;
import com.shanhh.demo.cache.service.UserCache;
import com.shanhh.demo.service.SecurityService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author dan
 * @since 2017-04-10 14:57
 */
@RestController
@RequestMapping("security")
public class SecurityController {

    @Resource
    private SecurityService securityService;
    @Resource
    private UserCache userCache;

    @RequestMapping(value = "signin", method = POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String signIn(
            @RequestParam String email,
            @RequestParam String password) {
        return securityService.signIn(email, password);
    }

    @RequestMapping(value = "signup", method = POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String signUp(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String nickname) {
        return securityService.signUp(email, password, nickname);
    }

    @RequestMapping(value = "info", method = POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User fetchUser(@CookieValue("session_id") String sessionId) {
        return securityService.fetchUser(sessionId);
    }

}
