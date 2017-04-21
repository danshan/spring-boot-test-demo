package com.shanhh.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author dan
 * @since 2017-04-10 14:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ApplicationTest {

    @Test
    public void contextLoads() {
    }

}