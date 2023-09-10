package com.hxd.fsystemback.service;

import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.common.JwtTokenUtils;
import com.hxd.fsystemback.dao.LogMapper;
import com.hxd.fsystemback.entity.Log;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.User;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.List;

@Service
public class LogService {
    @Resource
    LogMapper logMapper;


    void setLog (String logMsg) throws JsonProcessingException {
        Date time = new Date();
        String username = "";
        String ip = "";
        User user = JwtTokenUtils.getUserByToken();
        if(ObjectUtil.isNotNull(user)){
            username =user.getName();
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ip = request.getRemoteAddr();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(request);

        logMapper.addLog(username,logMsg,ip,time,json);
    }

    PageInfo<Log> getLog(Params params){
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Log> list = logMapper.getLog();
        return PageInfo.of(list);


    }
    PageInfo<Log> searchLog(Params params){
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Log> list = logMapper.searchLog(params.getKeyword());
        return PageInfo.of(list);

    }
}
