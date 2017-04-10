package com.shanhh.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author dan
 * @since 2017-04-10 14:57
 */
@RestController
@RequestMapping("security")
public class SecurityController {

    @RequestMapping(value = "signin", method = POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String signIn() {
        return "";
    }

    @RequestMapping(value = "signup", method = POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String signUp() {
        return "";
    }

}
