package com.hxd.fsystemback.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.dao.PartsMapper;
import com.hxd.fsystemback.dao.ProductMapper;
import com.hxd.fsystemback.dao.TechMapper;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.Product;
import com.hxd.fsystemback.entity.Tech;
import com.hxd.fsystemback.exception.TransactionException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlaneService {
    @Resource
    PartsMapper partsMapper;
    @Resource
    ProductMapper productMapper;
    @Resource
    TechMapper techMapper;

    public PageInfo<Product> getPlane(Params params){
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Product> list=productMapper.getPlane();
        return PageInfo.of(list);
    }
    @Transactional(rollbackFor = TransactionException.class)
    public void editPlane(Product product){
        productMapper.addPlane(product.getName(),product.getPlanNum(),product.getPlanDate());
        List<Tech> techList = techMapper.findTechByProductName(product.getName());
        int min;
        for(Tech tech : techList){
            min = (tech.getNum() * product.getPlanNum()) +tech.getPreWarn();
            partsMapper.setMin(tech.getPartsId(),min);
        }
    }
    @Transactional(rollbackFor = TransactionException.class)
    public void delPlane(Product product){
        product.setPlanNum(0);
        product.setPlanDate(null);
        editPlane(product);
    }
}
