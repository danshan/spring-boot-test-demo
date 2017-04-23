package com.shanhh.demo.bean.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author dan
 * @since 2017-04-22 07:15
 */
@Data
@ToString(exclude = "password")
@NoArgsConstructor
public class UserPO {
    private String email;
    private String nickname;
    private String password;
}
