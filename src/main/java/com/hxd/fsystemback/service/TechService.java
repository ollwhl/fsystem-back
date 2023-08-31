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
    public PageInfo<Tech> searchTechByProductName(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Tech> list = techMapper.searchTechByProductName(params.getKeyword());
        return PageInfo.of(list);
    }
    @Transactional(rollbackFor = TransactionException.class)
    public void addTech(List<Tech> techList) throws CustomException {
        String productName = techList.get(0).getProductName();
        Product product = productMapper.findProductByName(productName);
        if(product == null){
            productMapper.addProduct(productName,null,null);
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
                throw new CustomException("parts already exist in this product tech");
            }
            techMapper.addTech(product.getId(),parts.getId(),tech.getNum());
        }
    }
    public void editTechParts(Tech tech){
        techMapper.editTechParts(tech.getId(),tech.getNum());
    }
    public void delTechParts(Tech tech){
        techMapper.delTechParts(tech.getId());
    }


    public void addTechRow(Tech tech) throws CustomException {
        String productName = tech.getProductName();
        String partsName;
        Parts parts;
        Product product = productMapper.findProductByName(productName);
        if(product == null){
            throw new CustomException("product not exist");
        }
        if(tech.getProductName()!=productName){
            throw new CustomException("parts not compare product");
        }
        partsName = tech.getPartsName();
        parts = partsMapper.findPartsByName(partsName);
        if(parts == null){
            throw new CustomException("parts not exist");
        }
        if(techMapper.findTechByProductIdAndPartsId(product.getId(),parts.getId()) != null){
            throw new CustomException("parts already exist in this product tech");
        }
        techMapper.addTech(product.getId(),parts.getId(),tech.getNum());
    }
}
