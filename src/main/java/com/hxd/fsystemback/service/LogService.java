package com.hxd.fsystemback.service;

import cn.hutool.core.util.ObjectUtil;
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

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class LogService {
    @Resource
    LogMapper logMapper;


    public void setLog (String logMsg){
        Date time = new Date();
        String username = "";
        String ip = "";
        User user = JwtTokenUtils.getUserByToken();
        if(ObjectUtil.isNotNull(user)){
            username =user.getName();
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");

        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

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

    private String ipv6ToIpv4(String ipv6){
        try {
            // 获取IPv6地址
            InetAddress ip = InetAddress.getByName("IPv6地址");

            // 判断IPv6地址是否合法
//            boolean isIPv6 = ip.is;
//
//            if (!isIPv6) {
//                System.out.println("输入的不是一个合法的IPv6地址");
//                return;
//            }

            // 将IPv6地址转换为字节数组
            byte[] ipBytes = ip.getAddress();

            // 将字节数组转换为IPv4地址
            byte[] ipv4Bytes = Arrays.copyOfRange(ipBytes, 12, 16);

            // 输出IPv4地址
            String ipv4 = InetAddress.getByAddress(ipv4Bytes).getHostAddress();
            return ipv4;
        } catch (Exception e) {
            e.printStackTrace();
            return ipv6;
        }
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
