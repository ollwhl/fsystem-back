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
import com.hxd.fsystemback.entity.Tech;
import com.hxd.fsystemback.exception.CustomException;
import com.hxd.fsystemback.exception.TransactionException;
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
    public PageInfo<Parts> getConfirmParts(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Parts> list = partsMapper.getConfirmParts();
        return PageInfo.of(list);
    }
    public void editLost(Parts parts) throws CustomException {
        if (StrUtil.isBlank(parts.getName())){
            throw new CustomException("name为空");
        }
        partsMapper.editLost(parts.getName(),parts.getLost());
    }

    @Transactional(rollbackFor = TransactionException.class)
    public void dailyCheck(Product product) throws CustomException {
        if (StrUtil.isBlank(product.getName())){
            throw new CustomException("name为空");
        }
        productMapper.dailyCheck(product.getName(),product.getProduce());
        List<Tech> techList=techMapper.findTechByProductId(product.getId());
        Parts parts;
        for (Tech tech :techList){
            parts=partsMapper.findPartByID(tech.getPartsId());
            partsMapper.editPartNum(tech.getPartsId(), parts.getArrive()-(product.getProduce()*tech.getNum()));
        }
    }

    //name
    public void confirmArrive(String name) throws CustomException {
        if (StrUtil.isBlank(name)){
            throw new CustomException("name为空");
        }
        partsMapper.editPartConfirmNum(name,0);
    }
}
