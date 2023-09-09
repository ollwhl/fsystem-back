package com.hxd.fsystemback.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.common.Result;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.Product;
import com.hxd.fsystemback.exception.CustomException;
import com.hxd.fsystemback.service.PlaneService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/plane")

public class PlaneController {
    @Resource
    PlaneService planeService;

    @GetMapping("/getPlane")
    public Result getPlane(Params params){
        PageInfo<Product> list =planeService.getPlane(params);
        return Result.success(list);
    }
    @PostMapping("/editPlane")//传入name，planeNum,planeDate
    public Result editPlane(@RequestBody Product product) throws CustomException, JsonProcessingException {
        planeService.editPlane(product);
        return Result.success();
    }
    @PostMapping("/delPlane")//传入name
    public Result delPlane(@RequestBody Product product) throws CustomException, JsonProcessingException {
        planeService.delPlane(product);
        return Result.success();
    }

}
