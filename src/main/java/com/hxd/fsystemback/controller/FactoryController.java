package com.hxd.fsystemback.controller;

import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.common.Result;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.Parts;
import com.hxd.fsystemback.service.FactoryService;
import com.hxd.fsystemback.service.PartsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/factory")
public class FactoryController {
    @Resource
    FactoryService factoryService;

    @GetMapping("")
    public Result getConfirmParts(Params params){
        PageInfo<Parts> list = factoryService.getConfirmParts(params);
        return Result.success(list);
    }
}
