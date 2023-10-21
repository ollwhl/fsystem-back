package com.hxd.fsystemback.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.common.Result;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.Parts;
import com.hxd.fsystemback.entity.Product;
import com.hxd.fsystemback.exception.CustomException;
import com.hxd.fsystemback.service.LogService;
import com.hxd.fsystemback.service.PartsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PostExchange;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/parts")
public class PartsController {
    @Resource
    PartsService partsService;
    @Resource
    LogService logService;

    @GetMapping("/getParts")
    Result getPart(Params params){
        PageInfo<Parts> list = partsService.getPart(params);
        return Result.success(list);
    }
    @GetMapping("/getHalfProduct")
    Result getHalfProduct(Params params){
        PageInfo<Parts> list = partsService.getHalfProduct(params);
        return Result.success(list);
    }
    @GetMapping("/getProduct")
    Result getProduct(Params params){
        PageInfo<Product> list = partsService.getProduct(params);
        return Result.success(list);
    }
    @GetMapping("/getAllParts")
    Result getAllParts(Params params){
        PageInfo<Parts> list = partsService.getAllParts(params);
        return Result.success(list);
    }

    @GetMapping("/findProductByName")
    Result findProductByName(String name) throws CustomException {
        Product product = partsService.findProductByName(name);
        return Result.success(product);
    }
    @GetMapping("/findPartsByName")
    Result findPartsByName(String name) throws CustomException {
        Parts parts = partsService.findPartsByName(name);
        return Result.success(parts);
    }

    @GetMapping("/getBuyList")
    Result getBuyList(){
        List<Parts> list = partsService.getBuyList();
        return Result.success(list);
    }
    @GetMapping("/searchParts")
   public Result searchPart(Params params){
        PageInfo<Parts> list=partsService.searchPartByName(params);
        return Result.success(list);
    }

    @GetMapping("/searchProduct")
    public Result searchProduct(Params params){
        PageInfo<Product> list=partsService.searchProductByName(params);
        return Result.success(list);
    }
    @GetMapping("/getPartsByLost")
    public Result getPartsByLost(Params params){
        PageInfo<Parts> list=partsService.getPartsByLost(params);
        return Result.success(list);
    }

    @PostMapping("/count")//传入零件的name和confirm
    public Result countPart(@RequestBody Parts parts) throws CustomException, IOException {
        //System.out.println(parts);
        partsService.countPart(parts);

        return Result.success();
    }

    @PostMapping("/addParts")//传入id name standard note group，如果group == null 则添加product 不为空则按照group添加零件或半成品
    public Result addParts(@RequestBody Parts parts) throws CustomException {
        System.out.println(parts);
        partsService.addParts(parts);
        return Result.success();
    }

    @PostMapping("/delLost")
    public Result delLost(@RequestBody Parts parts){
        partsService.delLost(parts);
        return Result.success();
    }


}
