package com.hxd.fsystemback.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.common.Result;
import com.hxd.fsystemback.dao.PartsMapper;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.Parts;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PartsService {
    @Resource
    PartsMapper partsMapper;
    public PageInfo getPart(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Parts> list = partsMapper.getPart();
        return PageInfo.of(list);
    }
    public PageInfo<Parts> searchPartByName(Params params){
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Parts> list=partsMapper.searchPartsByName(params.getKeyword());
        return PageInfo.of(list);
    }
}
