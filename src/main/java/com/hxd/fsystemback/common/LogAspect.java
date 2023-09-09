package com.hxd.fsystemback.common;


import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hxd.fsystemback.entity.Log;
import com.hxd.fsystemback.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@Component
@Aspect
public class LogAspect {
    @Around("@annotation(autolog)")
    public Object doAround(ProceedingJoinPoint joinPoint,AutoLog autoLog) throws Throwable {
        String name = autoLog.value();
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

        Result result =(Result) joinPoint.proceed();
        Object data =result.getData();
        if(data instanceof User){
            username=((User) data).getName();
        }

        Log log = new Log();
        log.setLogMsg(username);
        log.setIp(ip);
        log.setTime(time);
        log.setLogMsg(name);
        log.setJson(json);
        return null;
    }
}
