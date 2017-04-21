package com.shanhh.demo.mapper;

/**
 * @author dan
 * @since 2017-04-21 21:03
 */

import com.shanhh.demo.bean.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE email = #{email} LIMIT 1")
    User loadByEmail(@Param("email") String email);

    @Insert("INSERT INTO user (email, nickname, password) VALUES (#{email}, #{nickname}, #{password})")
    int createUser(@Param("email") String email, @Param("nickname") String nickname, @Param("password") String password);

}
