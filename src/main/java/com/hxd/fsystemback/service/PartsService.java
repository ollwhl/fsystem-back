package com.hxd.fsystemback.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.common.JwtTokenUtils;
import com.hxd.fsystemback.dao.PartsMapper;
import com.hxd.fsystemback.dao.ProductMapper;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.Parts;
import com.hxd.fsystemback.entity.Product;
import com.hxd.fsystemback.exception.CustomException;
import com.hxd.fsystemback.exception.TransactionException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class PartsService {
    @Resource
    PartsMapper partsMapper;
    @Resource
    ProductMapper productMapper;
    public PageInfo<Parts> getPart(Params params) {
//        JwtTokenUtils.getGroupByToken(params.getToken())
//        String group =JwtTokenUtils.getGroupByToken(params.getToken());
//        if (group.equals("管理员")){
//            List<Parts> list = partsMapper.getAllPart();
//            return PageInfo.of(list);
//        }
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Parts> list = partsMapper.getPart("零件仓库");
        return PageInfo.of(list);
    }
    public PageInfo<Parts> getHalfProduct(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Parts> list = partsMapper.getPart("半成品仓库");
        return PageInfo.of(list);
    }

    public PageInfo<Parts> getAllParts(Params params){
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Parts> list = partsMapper.getAllPart();
        return PageInfo.of(list);
    }
    public PageInfo<Parts> searchPartByName(Params params){
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Parts> list=partsMapper.searchPartsByName(params.getKeyword(),JwtTokenUtils.getGroupByToken(params.getToken()));
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
    public void countPart(Params params) throws CustomException {
        Integer num;
        Parts parts = partsMapper.findPartByID(params.getId());
        System.out.println(parts);
        if (parts == null) {
            throw new CustomException("not find part");
        }
        if (params.getCountNum() < 0) {
            Integer confirmNum = parts.getConfirm() + params.getCountNum();
            System.out.println(confirmNum);
            partsMapper.updatePartConfirmNum(params.getId(), confirmNum);
        }
        num = parts.getNum() + params.getCountNum();
        partsMapper.updatePartNum(params.getId(), num);
    }

    public Product findProductByName(String name) throws CustomException {
        Product product = productMapper.findProductByName(name);
        if(product == null){
            throw new CustomException("no find Product");
        }
        return product;
    }
    public Parts findPartsByName(String name) throws CustomException {
        Parts parts = partsMapper.findPartsByName(name);
        if(parts == null){
            throw new CustomException("no find Parts");
        }
        return parts;
    }
}
