package com.hxd.fsystemback.controller;

import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.common.Result;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.User;
import com.hxd.fsystemback.exception.CustomException;
import com.hxd.fsystemback.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@CrossOrigin
@RestController//将返回数据变为jason格式
@RequestMapping("/user")//前缀
public class UserController {
    @Resource//从ioc中取出
    UserService userService;
//    @GetMapping("/stuff")
//    public Result getStuff(Params params){
//        PageInfo<User> list = userService.getStuff(params);
//        return Result.success(list);
//    }
//    @GetMapping("/admin")
//    public Result getAdmin(Params params){
//        PageInfo<User> list= userService.getAdmin(params);
//        return Result.success(list);
//    }
    @GetMapping("")
    public Result getAllUser(Params params){
        PageInfo<User> list= userService.getAllUsers(params);
        return Result.success(list);
    }
    @GetMapping("/admin/search")
    public Result searchAdmin(Params params){
        PageInfo<User> list= userService.getUserByName(params);
        return  Result.success(list);
    }
    @GetMapping("/stuff/search")
    public Result searchStuff(Params params){
        PageInfo<User> list= userService.getUserByName(params);
        return  Result.success(list);
    }
    @PostMapping("/add")
    public Result addUser(@RequestBody User user) throws CustomException {
        return userService.addUser(user);
    }
    @PostMapping("/login")
    public Result login(@RequestBody User user) throws CustomException {
        return userService.login(user);
    }

}
