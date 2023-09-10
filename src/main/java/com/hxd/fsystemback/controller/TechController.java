package com.hxd.fsystemback.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.common.Result;
import com.hxd.fsystemback.entity.*;
import com.hxd.fsystemback.exception.CustomException;
import com.hxd.fsystemback.service.TechService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RequestMapping("/tech")
@RestController
public class TechController {
    @Resource
    TechService techService;

    //返回Tech列表
    @GetMapping("")
    public Result getProductTech(Params params){
        PageInfo<Tech> list = techService.getProductTech(params);
        return Result.success(list);
    }
    @GetMapping("/search")
    public Result searchTechByProductName(Params params){
        PageInfo<Tech> list =techService.searchTechByProductName(params);
        return Result.success(list);
    }
//    @Deprecated
//    @PostMapping("/addTech")//传入每一条都包含{productName,partsName,num}的List
//    public Result addTech(@RequestBody List<Tech> list) throws CustomException {
//        techService.addTech(list);
//        return Result.success();
//    }

    @PostMapping("/addTechRow")//传入每一条都包含{productName,partsName,num}的List
    public Result addTechRow(@RequestBody Tech Tech) throws CustomException {
        techService.addTechRow(Tech);
        return Result.success();
    }

    @PostMapping("/edit")//传入id num
    public Result editTechParts(@RequestBody Tech tech) throws CustomException, IOException {
        techService.editTechParts(tech);
        return Result.success();
    }

    @PostMapping("/delParts")//传入id
    public Result delTechParts(@RequestBody Tech tech) throws CustomException, IOException {
        techService.delTechParts(tech);
        return Result.success();
    }
    @PostMapping("/test")//{productName,partsName,num}
    public Result test(@RequestBody String[] list) throws CustomException {
        System.out.println(list);
        return Result.success();
    }



}
