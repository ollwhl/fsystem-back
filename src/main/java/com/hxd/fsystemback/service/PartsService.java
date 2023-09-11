package com.hxd.fsystemback.service;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.io.IOException;
import java.util.List;
@Service
public class PartsService {
    @Resource
    PartsMapper partsMapper;
    @Resource
    ProductMapper productMapper;

    @Resource
    LogService logService;
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
    public PageInfo<Product> getProduct(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Product> list = productMapper.getProduct();
        return PageInfo.of(list);
    }
    public PageInfo<Parts> searchPartByName(Params params){
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Parts> list=partsMapper.searchPartsByName(params.getKeyword(),JwtTokenUtils.getUserByToken().getGroup());
        return PageInfo.of(list);
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

    @Transactional
    public void addParts(Parts parts) throws CustomException { //传入name standard note group，如果group == null 则添加product 不为空则按照group添加零件或半成品
        if (StrUtil.isBlank(parts.getName())){
            throw new CustomException("name为空");
        }
        if (parts.getGroup() == null){
            Product product = productMapper.findProductByName(parts.getName());
            //System.out.println(product);
            if (product != null){
                //System.out.println("product already exist");
                throw new CustomException("product already exist");
            }
            productMapper.addProduct(parts.getId(),parts.getName(),parts.getStandard(),parts.getNote());
            logService.setLog("添加了产品（id："+parts.getId()+"）（名字："+parts.getName()+") （规格："+parts.getStandard()+"）（描述："+parts.getNote()+")");
        }else{
            if (partsMapper.findPartsByName(parts.getName()) != null){
                throw new CustomException("parts already exist");
            }
            partsMapper.addPart(parts.getId(), parts.getName(),parts.getStandard(),parts.getGroup(),parts.getNote(), parts.getPreWarn());
            logService.setLog("添加了零件（id："+parts.getId()+"）（名字："+parts.getName()+") （仓库："+parts.getGroup()+"）（规格："+parts.getStandard()+"）（描述："+parts.getNote()+")（备件："+parts.getPreWarn()+")");
        }

    }

    @Transactional
    public void countPart(Parts parts) throws CustomException, IOException { //name confirm
        if (StrUtil.isBlank(parts.getName())){
            throw new CustomException("name为空");
        }
        Integer num;
        Parts thisParts = partsMapper.findPartsByName(parts.getName());
        //System.out.println(thisParts);
        if (thisParts == null) {
            throw new CustomException("not find part");
        }
        if (parts.getConfirm() < 0) {  //Confirm 为负数时为出库
            Integer confirmNum = thisParts.getConfirm() + parts.getConfirm();
            Integer min = thisParts.getMin() + parts.getConfirm();
            System.out.println(confirmNum);
            partsMapper.editPartConfirmNum(thisParts.getName(), confirmNum);
            partsMapper.editMin(thisParts.getId(), min);
            logService.setLog("出库了"+(-parts.getConfirm())+"个"+parts.getName()+" id为"+thisParts.getId());
        }else {
            logService.setLog("入库了"+parts.getConfirm()+"个"+parts.getName()+" id为"+thisParts.getId());
        }
        num = thisParts.getNum() + parts.getConfirm();
        partsMapper.editPartNum(thisParts.getId(), num);
    }
    @Transactional
    public void editPreWarn(Parts parts) throws CustomException {
        if (StrUtil.isBlank(parts.getName())){
            throw new CustomException("name为空");
        }
        partsMapper.editPreWarn(parts.getName(), parts.getPreWarn());
        logService.setLog("修改了 "+parts.getName()+" 的备件数量为 "+parts.getPreWarn());
    }

    public List<Parts> getBuyList(){
        return partsMapper.getBuyList();
    }


}
