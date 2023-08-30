package com.hxd.fsystemback.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.dao.PartsMapper;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.Parts;
import com.hxd.fsystemback.entity.Tech;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryService {
    @Resource
    PartsMapper partsMapper;
    public PageInfo<Parts> getConfirmParts(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Parts> list = partsMapper.getConfirmParts();
        return PageInfo.of(list);
    }
}
