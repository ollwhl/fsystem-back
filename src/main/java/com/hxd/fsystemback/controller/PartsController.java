package com.hxd.fsystemback.controller;

import com.hxd.fsystemback.common.Result;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.Parts;
import com.hxd.fsystemback.service.PartsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parts")
public class PartsController {
    @Resource
    PartsService partService;
    @GetMapping("/all")
    Result getAllParts(){
        List<Parts> list = partService.getAllParts();
        return Result.success(list);
    }
    @GetMapping("/search")
    Result getPartsByName(Params params){
        List<Parts> list = partService.getPartsByName(params.getKeyword());
        return Result.success(list);
    }
    @GetMapping("/add")
    Result addParts(Params params){

        return Result.success();
    }
}
