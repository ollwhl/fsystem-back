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

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    @GetMapping("/stuff")
    public Result getStuff(Params params){
        PageInfo<User> list = userService.getStuff(params);
        return Result.success(list);
    }
    @GetMapping("/admin")
    public Result getAdmin(Params params){
        PageInfo<User> list= userService.getAdmin(params);
        return Result.success(list);
    }
    @GetMapping("/admin/search")
    public Result searchAdmin(Params params){
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
