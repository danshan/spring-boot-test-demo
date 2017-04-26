package com.shanhh.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shanhh.demo.BaseTest;
import com.shanhh.demo.bean.dto.User;
import com.shanhh.demo.bean.po.UserPO;
import com.shanhh.demo.cache.service.UserCacheImpl;
import com.shanhh.demo.mapper.UserMapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
    @InjectMocks
    @Spy
    private UserCacheImpl userCache;
    @Mock
    private UserMapper userMapper;

    @Before
    public void beforeMethod() {
        when(stringRedisTemplate.opsForValue()).thenReturn(this.valueOperations);
    }

    @Test
    public void signIn_success() throws Exception {
        String email = mockStr(10);
        String password = mockStr(10);

        UserPO user = mockUserPO();
        user.setEmail(email);
        user.setPassword(password);
        when(userMapper.loadByEmail(any(String.class))).thenReturn(user);

        String sessionId = securityService.signIn(email, password);

        assertNotNull(sessionId);

        verify(userMapper, times(1)).loadByEmail(any(String.class));
        verify(userCache, times(1)).saveSession(any(User.class), any(String.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void signIn_incorrectPassword() throws Exception {
        String email = mockStr(10);
        String password = mockStr(10);

        UserPO user = mockUserPO();
        user.setEmail(email);
        user.setPassword(mockStr(10));
        when(userMapper.loadByEmail(any(String.class))).thenReturn(user);

        securityService.signIn(email, password);

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

        verify(userCache, times(1)).loadBySessionId(any(String.class));
        verify(userCache, never()).saveSession(any(User.class), any(String.class));
        verify(userMapper, never()).loadByEmail(any(String.class));
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
        UserPO userPO = mockUserPO();
        User user = new User();
        BeanUtils.copyProperties(userPO, user);
        return user;
    }

    private UserPO mockUserPO() {
        UserPO user = new UserPO();
        user.setNickname(mockStr(10));
        user.setEmail(mockEmail());
        user.setPassword(mockStr(10));
        return user;
    }


}
