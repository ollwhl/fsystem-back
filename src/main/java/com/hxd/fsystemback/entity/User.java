package com.hxd.fsystemback.entity;

import com.hxd.fsystemback.dao.UserMapper;
import com.hxd.fsystemback.exception.CustomException;
import jakarta.annotation.Resource;
import lombok.Data;
import org.apache.ibatis.jdbc.Null;

import java.util.Objects;


@Data
public class User {
    @Resource
    private UserMapper userMapper;

    private Integer id;
    private String name;
    private String password;
    private String phone;
    private Integer group;

    public User() {
    }
    public void add(User user) throws CustomException {
        //前台判断非空
        if(user.getName()==null||"".equals(user.getName())){
            throw new CustomException("UserName blank");
        }
        if(user.getPassword().length()<6){
            throw new CustomException("Password length < 6");
        }
        if(user.getPhone().length()<11){
            throw new CustomException("Phone number length < 11");
        }
        if (user.getGroup()>2||user.getGroup()<0){
            throw new CustomException("Unknow User Group");
        }
        User thisUser=userMapper.findUserByName(user.getName());
        if(thisUser!= null){
            throw new CustomException("User Exist");
        }
        userMapper.insertUser(user);


    }
    public User login(User user) throws CustomException {
        User thisUser = userMapper.findUserByName(user.getName());
        if(thisUser==null){
            throw new CustomException("User not Exist");
        }
        if(Objects.equals(thisUser.getPassword(), user.getPassword())){
            return thisUser;
        }else{
            throw new CustomException("Password not right");
        }

    }
}
