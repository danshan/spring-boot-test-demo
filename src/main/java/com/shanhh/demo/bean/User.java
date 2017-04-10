package com.shanhh.demo.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author dan
 * @since 2017-04-10 15:05
 */
@Data
@NoArgsConstructor
@ToString
public class User implements Serializable {
    private String email;
    private String nickname;
}
