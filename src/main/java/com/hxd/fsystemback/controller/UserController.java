package com.hxd.fsystemback.controller;

import com.hxd.fsystemback.common.Result;
import com.hxd.fsystemback.entity.User;
import com.hxd.fsystemback.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    @Resource
    UserService userService;
    @GetMapping("/Stuff")
    public Result getStuff(){
        List<User> list = userService.getStuff();
        Result resault = new Result();
        return Result.success(list);
    }
}
