package com.hxd.fsystemback.controller;

import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.common.Result;
import com.hxd.fsystemback.entity.*;
import com.hxd.fsystemback.exception.CustomException;
import com.hxd.fsystemback.service.TechService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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

    //传入name standard note group，如果group == null 则添加product 不为空则按照group添加零件或半成品
    @PostMapping("/addParts")
    public Result addParts(@RequestBody Parts parts) throws CustomException {
        techService.addParts(parts);
        return Result.success();
    }

    //传入包含{productName,partsName,num}的List
    @PostMapping("/addTech")
    public Result addTech(@RequestBody List<Tech> list) throws CustomException {
        techService.addTech(list);
        return Result.success();
    }

    @PostMapping("/edit")
    public Result editTechParts(@RequestBody Tech tech) throws CustomException {
        techService.editTechParts(tech);
        return Result.success();
    }

    @PostMapping("/delParts")
    public Result delTechParts(@RequestBody Tech tech) throws CustomException {
        techService.delTechParts(tech);
        return Result.success();
    }



}
