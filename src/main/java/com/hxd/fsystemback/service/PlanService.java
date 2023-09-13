package com.hxd.fsystemback.service;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.dao.PartsMapper;
import com.hxd.fsystemback.dao.ProductMapper;
import com.hxd.fsystemback.dao.TechMapper;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.Product;
import com.hxd.fsystemback.entity.Tech;
import com.hxd.fsystemback.exception.CustomException;
import com.hxd.fsystemback.exception.TransactionException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class PlanService {
    @Resource
    PartsMapper partsMapper;
    @Resource
    ProductMapper productMapper;
    @Resource
    TechMapper techMapper;
    @Resource
    LogService logService;

    public PageInfo<Product> getPlane(Params params){
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Product> list=productMapper.getPlane();
        return PageInfo.of(list);
    }
    @Transactional
    public void editPlane(Product product) throws IOException, CustomException { //name，planeNum,planeDate
        if (StrUtil.isBlank(product.getName())){
            throw new CustomException("name为空");
        }
        productMapper.editPlane(product.getName(),product.getPlanNum(),product.getPlanDate());
        List<Tech> techList = techMapper.findTechByProductName(product.getName());
        int min;
        for(Tech tech : techList){
            min = (tech.getNum() * product.getPlanNum()) +tech.getPreWarn();
            partsMapper.editMin(tech.getPartsId(),min);
        }
        logService.setLog("修改了 "+product.getName()+" 的计划，计划期限为 "+product.getPlanDate()+" ，计划数量为 "+product.getPlanNum());
    }
    @Transactional
    public void delPlane(Product product) throws IOException, CustomException {//name
        if (StrUtil.isBlank(product.getName())){
            throw new CustomException("name为空");
        }
        product.setPlanNum(0);
        product.setPlanDate(null);
        editPlane(product);
        productMapper.editProduced(product.getName(),0);
        logService.setLog("删除了 "+product.getName()+" 的计划");
    }
}
