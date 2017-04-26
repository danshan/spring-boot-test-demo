package com.shanhh.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dan
 * @since 2017-04-10 14:35
 */
@SpringBootApplication
public class Application {

    private Application() {}
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
