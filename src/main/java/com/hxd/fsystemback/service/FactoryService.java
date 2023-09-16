package com.hxd.fsystemback.service;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.dao.PartsMapper;
import com.hxd.fsystemback.dao.ProductMapper;
import com.hxd.fsystemback.dao.TechMapper;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.Parts;
import com.hxd.fsystemback.entity.Product;
import com.hxd.fsystemback.exception.CustomException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FactoryService {
    @Resource
    PartsMapper partsMapper;
    @Resource
    ProductMapper productMapper;
    @Resource
    TechMapper techMapper;

    @Resource
    LogService logService;
    public PageInfo<Parts> getConfirmParts(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Parts> list = partsMapper.getConfirmParts();
        return PageInfo.of(list);
    }

    @Transactional
    public void editLost(Parts parts) throws CustomException {
        if (StrUtil.isBlank(parts.getName())){
            throw new CustomException("name为空");
        }
        Parts thisParts = partsMapper.findPartsByName(parts.getName());
        partsMapper.editLost(parts.getName(),thisParts.getLost()+parts.getLost());
        partsMapper.editMin(thisParts.getId(), thisParts.getMin()+ parts.getLost());
        logService.setLog("损耗了"+parts.getLost()+"个"+parts.getName());
    }


    @Transactional//name produced
    public void dailyCheck(Product product) throws CustomException {
        if (StrUtil.isBlank(product.getName())){
            throw new CustomException("name为空");
        }
        Product thisProduct = productMapper.findProductByName(product.getName());
        productMapper.editProduced(product.getName(),product.getProduced()+thisProduct.getProduced());
        productMapper.editProductConfirmNum(product.getName(),thisProduct.getProductConfirm()+product.getProduced());
//        List<Tech> techList=techMapper.findTechByProductId(product.getId());
//        Parts parts;
//        for (Tech tech :techList){
//            parts=partsMapper.findPartByID(tech.getPartsId());
//            partsMapper.editMin(parts.getId(), parts.getMin()-tech.getNum()*product.getProduce());
//        }
        logService.setLog("填写了生产进度 今日生产了"+product.getProduced()+"个"+product.getName());
    }

    //name
    @Transactional
    public void confirmParts(Parts parts) throws CustomException {
        if (StrUtil.isBlank(parts.getName())){
            throw new CustomException("name为空");
        }
        partsMapper.editPartConfirmNum(parts.getName(), 0);
        logService.setLog("确认了"+parts.getName()+"的入库");
    }

    //name
    @Transactional
    public void confirmProduct(Product product) throws CustomException {
        if (StrUtil.isBlank(product.getName())){
            throw new CustomException("name为空");
        }
        Product thisProduct = productMapper.findProductByName(product.getName());
        productMapper.editProductNum(product.getName(), thisProduct.getNum()+thisProduct.getProductConfirm());
        productMapper.editProductConfirmNum(product.getName(),0);
        logService.setLog("确认了"+product.getName()+"的入库");
    }

    //name num
    @Transactional
    public void reduceProduct(Product product) throws CustomException {
        if (StrUtil.isBlank(product.getName())){
            throw new CustomException("name为空");
        }
        Product thisProduct=productMapper.findProductByName(product.getName());
        productMapper.editProductNum(product.getName(),thisProduct.getNum()- product.getNum());
        logService.setLog("出库了"+product.getNum()+"个"+product.getName());
    }
}
