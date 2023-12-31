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
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class TechService {
    @Resource
    TechMapper techMapper;
    @Resource
    PartsMapper partsMapper;
    @Resource
    ProductMapper productMapper;
    @Resource
    LogService logService;


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

    public PageInfo<Tech> getTechByWithPlan(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Tech> list = techMapper.getTechWithPlan();
        return PageInfo.of(list);
    }
//    @Transactional(rollbackFor = TransactionException.class)
//    public void addTech(List<Tech> techList) throws CustomException {
//        String productName = techList.get(0).getProductName();
//        Product product = productMapper.findProductByName(productName);
//        if(product == null){
//            productMapper.addProduct(productName,null,null);
//        }
//        String partsName;
//        Parts parts;
//        for(Tech tech : techList){
//            if(tech.getProductName()!=productName){
//                throw new CustomException("parts not compare product");
//            }
//            partsName = tech.getPartsName();
//            parts = partsMapper.findPartsByName(partsName);
//            if(parts == null){
//                throw new CustomException("parts not exist");
//            }
//            if(techMapper.findTechByProductIdAndPartsId(product.getId(),parts.getId()) != null){
//                throw new CustomException("parts already exist in this product tech");
//            }
//            techMapper.addTech(product.getId(),parts.getId(),tech.getNum());
//        }
//    }
    @Transactional
    public void editTechParts(Tech tech) throws CustomException {

        Tech thisTech = techMapper.findTechByID(tech.getId());
        if (thisTech == null){
            throw new CustomException("Tec not exist");
        }
        if(tech.getPreWarn()>0){
            partsMapper.editPreWarn(thisTech.getPartsName(),tech.getPreWarn());
        }
        techMapper.editTechParts(tech.getId(),tech.getNum());
        logService.setLog("修改了 "+thisTech.getProductName()+" 的产品构成中的 "+thisTech.getPartsName()+" 的数量为 "+thisTech.getNum());
    }
    @Transactional
    public void delTechParts(Tech tech) throws IOException, CustomException {
        Tech thisTech = techMapper.findTechByID(tech.getId());
        if(!productMapper.getPlane().isEmpty()){
            throw new CustomException("该总成已有生产计划，请联系计划部，完成或删除该总成生产计划再删除构成");
        }
        techMapper.delTechParts(tech.getId());
        logService.setLog("删除了 "+thisTech.getProductName()+" 的产品构成中的 "+thisTech.getPartsName());
    }


    @Transactional
    public void addTechRow(Tech tech) throws CustomException {
        String productName = tech.getProductName();
        String partsName;
        Parts parts;
        Product product = productMapper.findProductByName(productName);
        if(product == null){
            int productId=tech.getProductId();
            if (productId == 0){
                throw new CustomException("总成不存在 请重新添加");
            }
//            productMapper.addProduct(productId,productName,tech.getProductStandard(),tech.getProductNote());
//            product = productMapper.findProductByName(productName);
//            logService.setLog("添加了产品（id："+product.getId()+"）（名字："+product.getName()+") （规格："+product.getStandard()+"）（描述："+product.getNote());
        }
        partsName = tech.getPartsName();
        parts = partsMapper.findPartsByName(partsName);
        if(parts == null){
            throw new CustomException("零件不存在");
        }
        if(techMapper.findTechByProductIdAndPartsId(product.getId(),parts.getId()) != null){
            throw new CustomException("parts already exist in this product tech");
        }
        techMapper.addTech(product.getId(),parts.getId(),tech.getNum());
        logService.setLog("添加了总成构成（总成："+product.getName()+"）（零件："+parts.getName()+"）（数目："+tech.getNum()+"）");
    }

}
