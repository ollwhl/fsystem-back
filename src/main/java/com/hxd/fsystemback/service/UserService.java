package com.hxd.fsystemback.service;

import com.hxd.fsystemback.common.Result;
import com.hxd.fsystemback.dao.UserMapper;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.User;
import com.hxd.fsystemback.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.*;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public PageInfo<User> getAllUsers(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<User> list=userMapper.getAllUsers();
        return PageInfo.of(list);
    }
    public PageInfo<User> getStuff(Params params){
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<User> list=userMapper.getStuff();
        return PageInfo.of(list);
    }

    public PageInfo<User> getAdmin(Params params) {
        List<User> list=userMapper.getAdmin();
        //System.out.println(list);
        PageHelper.startPage(params.getPageNum(),params.getPageSize());


        return PageInfo.of(list);
    }
    public PageInfo<User> getUserByName(Params params){
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<User> list=userMapper.getUsersByName(params.getKeyword());
        return PageInfo.of(list);
    }
    public int editUser(User user) throws CustomException {
        User thisUser=userMapper.findUserByName(user.getName());
        if(thisUser!= null){
            throw new CustomException("edit:User name Exist");
        }
        return userMapper.editUser(user.getId(),user.getName(), user.getPassword(),user.getGroup(),user.getPhone(),user.getNote());
    }
    public int deleteUser(User user){
        System.out.println();
        return userMapper.deleteUser(user.getId());
    }

    public Result addUser(User user) throws CustomException {
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
        User thisUser=userMapper.findUserByName(user.getName());
        if(thisUser!= null){
            throw new CustomException("User Exist");
        }
        if(userMapper.addUser(user.getName(), user.getPassword(),user.getGroup(),user.getPhone(),user.getNote()) != 1){
            throw new CustomException("未知原因添加失败");
        }
        return Result.success();
    }
    public Result login(User user) throws CustomException {
        User thisUser = userMapper.findUserByName(user.getName());
        if(thisUser==null){
            throw new CustomException("User not Exist");
        }
        if(Objects.equals(thisUser.getPassword(), user.getPassword())){
            return Result.success(thisUser);
        }else{
            throw new CustomException("Password not right");
        }
    }
}
