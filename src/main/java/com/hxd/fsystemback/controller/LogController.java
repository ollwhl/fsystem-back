package com.hxd.fsystemback.controller;

import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.common.Result;
import com.hxd.fsystemback.entity.Log;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.service.LogService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/log")
public class LogController {
    @Resource
    LogService logService;
    @GetMapping("")
    Result getLog(Params params){
        PageInfo<Log> list = logService.getLog(params);
        return Result.success(list);
    }
}
