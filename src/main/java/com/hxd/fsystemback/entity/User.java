package com.hxd.fsystemback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hxd.fsystemback.dao.UserMapper;
import com.hxd.fsystemback.exception.CustomException;
import jakarta.annotation.Resource;
import lombok.Data;
import org.apache.ibatis.jdbc.Null;

import java.util.Objects;


@Data
public class User {
    @Resource
    @JsonIgnore
    private UserMapper userMapper;

    private Integer id;
    private String name;
    private String password;
    private String phone;
    private String note;
    private String group;

    public User() {
    }
}
