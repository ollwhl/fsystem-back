package com.hxd.fsystemback.service;

import com.hxd.fsystemback.dao.UserMapper;
import com.hxd.fsystemback.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> getUsers() {
        return userMapper.getAllUsers();
    }
    public List<User> getStuff(){
        return userMapper.getStuff();
    }
}
