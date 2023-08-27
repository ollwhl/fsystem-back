package com.hxd.fsystemback.controller;

import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.common.Result;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.Parts;
import com.hxd.fsystemback.exception.CustomException;
import com.hxd.fsystemback.service.PartsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PostExchange;

@CrossOrigin
@RestController
@RequestMapping("/parts")
public class PartsController {
    @Resource
    PartsService partService;
    @GetMapping("")
    Result getPart(Params params){
        PageInfo<Parts> list = partService.getPart(params);
        return Result.success(list);
    }
    @GetMapping("/search")
   public Result searchPart(Params params){
        PageInfo<Parts> list=partService.searchPartByName(params);
        return Result.success(list);
    }

    @PostMapping("/count")
    public Result countPart(@RequestBody Params params) throws CustomException {
        System.out.println(params);
        partService.countPart(params);
        return Result.success();
    }


}
