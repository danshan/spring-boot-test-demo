package com.shanhh.demo.service;

import com.shanhh.demo.bean.User;
import com.shanhh.demo.jedis.CacheException;
import com.shanhh.demo.jedis.JedisCallback;
import com.shanhh.demo.jedis.JedisService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import redis.clients.jedis.JedisPool;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * @author dan
 * @since 2017-04-10 15:15
 */
@RunWith(MockitoJUnitRunner.class)
public class SecurityServiceImplTest {

    @InjectMocks
    private SecurityServiceImpl securityService;

    @Mock
    private JedisService jedisService;

    @Before
    public void beforeMethod() {

    }

    @Test
    public void signIn_() throws Exception {
        String usenname = "username";
        String password = "password";
    }

    @Test
    public void signUp() throws Exception {
    }

    @Test
    public void fetchUser_foundInCache() throws Exception {
        String sessionId = "sessionId";

        User user = new User();
        user.setEmail("email");
        user.setNickname("nickname");


        when(jedisService.doJedis(any(JedisCallback.class), any(JedisPool.class))).thenReturn(user);
        User result = securityService.fetchUser(sessionId);

        assertThat(result.getEmail(), is(user.getEmail()));
        assertThat(result.getNickname(), is(user.getNickname()));
    }

    @Test
    public void fetchUser_redisFailed() {
        String sessionId = "sessionId";

        when(jedisService.doJedis(any(JedisCallback.class), any(JedisPool.class))).thenThrow(new CacheException("failed"));

        User result = securityService.fetchUser(sessionId);
        assertNull(result);
    }

}