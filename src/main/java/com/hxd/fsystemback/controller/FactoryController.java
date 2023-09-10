package com.hxd.fsystemback.controller;

import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.common.Result;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.Parts;
import com.hxd.fsystemback.entity.Product;
import com.hxd.fsystemback.exception.CustomException;
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
    public Result getFactory(Params params){
        PageInfo<Parts> list = factoryService.getConfirmParts(params);
        return Result.success(list);
    }
    @GetMapping("editLost")//name lost
    public Result editLost(Parts parts) throws CustomException {
        factoryService.editLost(parts);
        return Result.success();
    }
    @GetMapping("dailyCheck")//name produce
    public Result dailyCheck(Product product) throws CustomException {
        factoryService.dailyCheck(product);
        return Result.success();
    }
    @GetMapping("confirmArrive")//name
    public Result confirmCheck(String name) throws CustomException {
        factoryService.confirmArrive(name);
        return Result.success();
    }
}
