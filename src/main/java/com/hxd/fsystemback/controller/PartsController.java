package com.hxd.fsystemback.controller;

import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.common.Result;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.Parts;
import com.hxd.fsystemback.service.PartsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/parts")
public class PartsController {
    @Resource
    PartsService partService;
    @GetMapping("/part")
    Result getPart(Params params){
        PageInfo<Parts> list = partService.getPart(params);
        return Result.success(list);
    }
    @GetMapping("/part/search")
   public Result searchPart(Params params){
        PageInfo<Parts> list=partService.searchPartByName(params);
        return Result.success(list);
    }


}
