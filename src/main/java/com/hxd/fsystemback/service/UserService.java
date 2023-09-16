package com.hxd.fsystemback.service;

import com.hxd.fsystemback.common.JwtTokenUtils;
import com.hxd.fsystemback.common.Result;
import com.hxd.fsystemback.dao.LogMapper;
import com.hxd.fsystemback.dao.UserMapper;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.User;
import com.hxd.fsystemback.exception.CustomException;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.*;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserMapper userMapper;

    @Resource
    LogService logService;

    @Resource
    LogMapper logMapper;

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
    public PageInfo<User> getUserByName(Params params){//按名搜索返回列表
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<User> list=userMapper.getUsersByName(params.getKeyword());
        return PageInfo.of(list);
    }
    public Result editUser(User user) throws CustomException {
        User thisUser=userMapper.findUserByName(user.getName());
        if(thisUser!= null){
            throw new CustomException("edit:User name Exist");
        }
        userMapper.editUser(user.getId(),user.getName(), user.getPassword(),user.getGroup(),user.getPhone(),user.getNote());
        return Result.success();
    }
    public int deleteUser(User user){

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

        User thisUser;
        if (user.getId() != null){
            thisUser=userMapper.findUserById(user.getId());
            userMapper.editUser(user.getId(), user.getName(), user.getPassword(),user.getGroup(),user.getPhone(),user.getNote());
            logService.setLog("修改了用户信息 修改前为（name："+thisUser.getName()+"）（password："+thisUser.getPassword()+"）（group："+thisUser.getGroup()+"）（phone："+thisUser.getPhone()+"）（note："+thisUser.getNote()+"）" +
                    "修改后为（name："+user.getName()+"）（password："+user.getPassword()+"）（group："+user.getGroup()+"）（phone："+user.getPhone()+"）（note："+user.getNote()+")");
            return Result.success();
        }

        thisUser=userMapper.findUserByName(user.getName());
        if(thisUser != null){
            throw new CustomException("User Exist");
        }
        userMapper.addUser(user.getName(), user.getPassword(),user.getGroup(),user.getPhone(),user.getNote());
        logService.setLog("新增用户（name："+user.getName()+"）（password："+user.getPassword()+"）（group："+user.getGroup()+"）（phone："+user.getPhone()+"）（note："+user.getNote()+")");
        return Result.success();

    }
    public Result login(User user) throws CustomException {
        User thisUser = userMapper.findUserByName(user.getName());
        if(thisUser==null){
            throw new CustomException("User not Exist");
        }
        if(Objects.equals(thisUser.getPassword(), user.getPassword())){
            thisUser.setToken(JwtTokenUtils.genToken(thisUser.getId().toString(),thisUser.getPassword()));
            logService.setLog( thisUser.getName()+" 登陆了" );
            return Result.success(thisUser);
        }else{
            throw new CustomException("Password not right");
        }
    }
    public User findUserById(int id){
        return userMapper.findUserById(id);
    }
}
