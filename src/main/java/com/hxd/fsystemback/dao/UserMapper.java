package com.hxd.fsystemback.dao;

import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.User;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user")
    List<User> getAllUsers();
    @Select("SELECT * FROM user WHERE `group` = '2'")
    List<User> getStuff();
    @Select("SELECT * FROM user WHERE `group` = '1'")
    List<User> getAdmin();
    @Select("SELECT * FROM user WHERE name LIKE #{keyword} OR phone Like #{keyword}")
    List<User> getUsersByName(@Param("keyword") String keyword);
    @Select("SELECT * FROM user WHERE name = #{name}")
    User findUserByName(@Param("name") String name);
    @Insert("INSERT INTO user (`name`, `password`, `phone`, `group`, `note`) VALUES (#{name},#{password},#{phone},#{group},#{note});")
    int addUser(@Param("name") String name,@Param("password") String password,@Param("group") String group,@Param("phone") String phone,@Param("note") String note);
    @Update("UPDATE user SET `name` = #{name}, `password` = #{password}, `phone` = #{phone}, `group` = #{group}, `note` = #{note} WHERE (`id` = #{id});")
    int editUser(@Param("id") Integer id, @Param("name") String name,@Param("password") String password,@Param("group") String group,@Param("phone") String phone, @Param("note") String note);
    @Delete("DELETE FROM user WHERE (`id` = #{id});")
    int deleteUser(@Param(("id")) Integer id);
}