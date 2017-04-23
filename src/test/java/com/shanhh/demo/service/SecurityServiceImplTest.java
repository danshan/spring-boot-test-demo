package com.shanhh.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shanhh.demo.BaseTest;
import com.shanhh.demo.bean.dto.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * @author dan
 * @since 2017-04-10 15:15
 */
public class SecurityServiceImplTest extends BaseTest {

    @InjectMocks
    private SecurityServiceImpl securityService;

    @Mock
    private StringRedisTemplate stringRedisTemplate;
    @Mock
    private ValueOperations valueOperations;
    @Spy
    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void beforeMethod() {
        when(stringRedisTemplate.opsForValue()).thenReturn(this.valueOperations);
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

        User mockUser = mockUser();
        when(valueOperations.get(any(String.class))).thenReturn(OBJECT_MAPPER.writeValueAsString(mockUser));

        User result = securityService.fetchUser(sessionId);

        assertThat(result.getEmail(), is(mockUser.getEmail()));
        assertThat(result.getNickname(), is(mockUser.getNickname()));
    }

    @Test
    public void fetchUser_parseJsonFailed() {
        String sessionId = "sessionId";

        when(valueOperations.get(any(String.class))).thenReturn("bad format json in cache");

        User result = securityService.fetchUser(sessionId);
        assertNull(result);
    }

    @Test
    public void fetchUser_redisFailed() {
        String sessionId = "sessionId";

        when(valueOperations.get(any(String.class))).thenThrow(new IllegalArgumentException("failed"));

        User result = securityService.fetchUser(sessionId);
        assertNull(result);
    }

    private User mockUser() {
        User user = new User();
        user.setNickname(mockStr(10));
        user.setEmail(mockEmail());

        return user;
    }

}