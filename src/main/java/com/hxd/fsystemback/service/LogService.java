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

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class LogService {
    @Resource
    LogMapper logMapper;


    public void setLog (String logMsg) throws IOException {
        Date time = new Date();
        String username = "";
        String ip = "";
        User user = JwtTokenUtils.getUserByToken();
        if(ObjectUtil.isNotNull(user)){
            username =user.getName();
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ip = request.getRemoteAddr();

        //String json = readJsonPayload(request);

        logMapper.addLog(username,logMsg,ip,time);
    }

    public PageInfo<Log> getLog(Params params){
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Log> list = logMapper.getLog();
        return PageInfo.of(list);


    }
    PageInfo<Log> searchLog(Params params){
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Log> list = logMapper.searchLog(params.getKeyword());
        return PageInfo.of(list);

    }
//    private String readJsonPayload(HttpServletRequest request) throws IOException {
//        StringBuilder stringBuilder = new StringBuilder();
//        BufferedReader bufferedReader = null;
//
//        try {
//            bufferedReader = request.getReader();
//            char[] charBuffer = new char[128];
//            int bytesRead;
//
//            while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
//                stringBuilder.append(charBuffer, 0, bytesRead);
//            }
//        } finally {
//            if (bufferedReader != null) {
//                bufferedReader.close();
//            }
//        }
//
//        return stringBuilder.toString();
//    }
}
