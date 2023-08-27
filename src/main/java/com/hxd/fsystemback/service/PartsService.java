package com.hxd.fsystemback.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.common.JwtTokenUtils;
import com.hxd.fsystemback.common.Result;
import com.hxd.fsystemback.dao.PartsMapper;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.Parts;
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
    public PageInfo getPart(Params params) {
        //JwtTokenUtils.getGroupByToken(params.getToken())
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Parts> list = partsMapper.getPart(JwtTokenUtils.getGroupByToken(params.getToken()));
        return PageInfo.of(list);
    }
    public PageInfo<Parts> searchPartByName(Params params){
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Parts> list=partsMapper.searchPartsByName(params.getKeyword(),JwtTokenUtils.getGroupByToken(params.getToken()));
        return PageInfo.of(list);
    }

    @Transactional(rollbackFor = TransactionException.class)
    public void countPart(Params params) throws CustomException {
        Integer num;
        Parts parts = partsMapper.searchPartByID(params.getId());
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
}
