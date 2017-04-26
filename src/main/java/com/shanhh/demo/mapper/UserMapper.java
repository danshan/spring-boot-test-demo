package com.shanhh.demo.mapper;

/**
 * @author dan
 * @since 2017-04-21 21:03
 */

import com.shanhh.demo.bean.po.UserPO;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT email, nickname, password FROM user WHERE email = #{email} LIMIT 1")
    UserPO loadByEmail(@Param("email") String email);

    @Insert("INSERT INTO user (email, nickname, password) VALUES (#{email}, #{nickname}, #{password})")
    int createUser(@Param("email") String email, @Param("nickname") String nickname, @Param("password") String password);

}
