package com.hxd.fsystemback.dao;

import com.hxd.fsystemback.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user")
    List<User> getAllUsers();
    @Select("SELECT * FROM user WHERE group = 2")
    List<User> getStuff();
    @Select("SELECT * FROM user WHERE group = 1")
    List<User> getAdmin();
    @Select("SELECT * FROM user WHERE name LIKE #{keyword}")
    List<User> getUsersByName(@Param("keyword") String keyword);
    @Select("SELECT * FROM user WHERE name +#{name}")
    User findUserByName(@Param("name") String name);

    void insertUser(User user);

}