package com.hxd.fsystemback.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.dao.PartsMapper;
import com.hxd.fsystemback.dao.ProductMapper;
import com.hxd.fsystemback.dao.TechMapper;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.Parts;
import com.hxd.fsystemback.entity.Product;
import com.hxd.fsystemback.entity.Tech;
import com.hxd.fsystemback.exception.CustomException;
import com.hxd.fsystemback.exception.TransactionException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.List;

@Service
public class TechService {
    @Resource
    TechMapper techMapper;
    @Resource
    PartsMapper partsMapper;
    @Resource
    ProductMapper productMapper;
    public PageInfo<Tech> getProductTech(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Tech> list= techMapper.getProductTech();
        return PageInfo.of(list);
    }
    public void addParts(Parts parts) throws CustomException {
        if (parts.getGroup() == null){
            Product product = productMapper.findProductByName(parts.getName());
            //System.out.println(product);
        if (product != null){
                //System.out.println("product already exist");
                throw new CustomException("product already exist");
            }
            productMapper.addProduct(parts.getName(),parts.getStandard(),parts.getNote());
        }else{
            if (partsMapper.findPartsByName(parts.getName()) != null){
                throw new CustomException("parts already exist");
            }
            partsMapper.addPart(parts.getName(),parts.getStandard(),parts.getGroup(),parts.getNote());
        }

    }
    @Transactional(rollbackFor = TransactionException.class)
    public void addTech(List<Tech> techList) throws CustomException {
        String productName = techList.get(0).getProductName();
        Product product = productMapper.findProductByName(productName);
        if(product == null){
            throw new CustomException("product not exist");
        }

        String partsName;
        Parts parts;
        for(Tech tech : techList){
            if(tech.getProductName()!=productName){
                throw new CustomException("parts not compare product");
            }
            partsName = tech.getPartsName();
            parts = partsMapper.findPartsByName(partsName);
            if(parts == null){
                throw new CustomException("parts not exist");
            }
            if(techMapper.findTechByProductIdAndPartsId(product.getId(),parts.getId()) != null){
                throw new CustomException("parts already exist in this tech");
            }
            techMapper.addTech(product.getId(),parts.getId(),tech.getNum());
        }
    }

}
