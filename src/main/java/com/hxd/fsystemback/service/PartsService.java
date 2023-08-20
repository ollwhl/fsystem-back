package com.hxd.fsystemback.service;

import com.hxd.fsystemback.dao.PartsMapper;
import com.hxd.fsystemback.entity.Parts;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PartsService {
    @Resource
    PartsMapper partsMapper;
    public List<Parts> getAllParts() {
        return partsMapper.getAllParts();
    }
    public List<Parts> getPartsByName(String keyword){
        return partsMapper.getPartsByName(keyword);
    }
}
