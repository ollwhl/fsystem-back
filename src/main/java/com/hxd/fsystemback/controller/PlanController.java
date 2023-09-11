package com.hxd.fsystemback.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.common.Result;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.Product;
import com.hxd.fsystemback.exception.CustomException;
import com.hxd.fsystemback.service.PlanService;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/plan")

public class PlanController {//plane是写后端的人写的不关我前端的事
    @Resource
    PlanService planeService;

    @GetMapping("/getPlan")
    public Result getPlane(Params params){
        PageInfo<Product> list =planeService.getPlane(params);
        return Result.success(list);
    }
    @PostMapping("/editPlan")//传入name，planeNum,planeDate
    public Result editPlane(@RequestBody Product product) throws CustomException, IOException {
        System.out.println(product);
        planeService.editPlane(product);
        return Result.success();
    }
    @PostMapping("/delPlan")//传入name
    public Result delPlane(@RequestBody Product product) throws CustomException, IOException {
        planeService.delPlane(product);
        return Result.success();
    }

}
