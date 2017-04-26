package com.shanhh.demo.mapper;

import com.shanhh.demo.BaseTest;
import com.shanhh.demo.bean.po.UserPO;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author dan
 * @since 2017-04-21 21:11
 */
@SpringBootTest
public class UserMapperTest extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void loadByEmail() throws Exception {
        String email = mockEmail();
        String password = mockStr(32);

        int affect = userMapper.createUser(email, "nickname", password);
        assertTrue(affect > 0);

        UserPO user = userMapper.loadByEmail(email);
        assertThat(user.getEmail(), is(email));
        assertThat(user.getPassword(), is(password));

    }

}
