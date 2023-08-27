package com.hxd.fsystemback.common;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hxd.fsystemback.entity.User;
import com.hxd.fsystemback.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtils {
    private static UserService staticUserService;
    private static final Logger log = LoggerFactory.getLogger(JwtTokenUtils.class);
    @Resource
    private UserService userService;

    @PostConstruct
    public void setUserServiceStatic(){
        staticUserService = userService;
    }

    public static String genToken(String userId,String sign){
        return JWT.create().withAudience(userId)
                .withExpiresAt(DateUtil.offsetHour(new Date(),2))
                .sign(Algorithm.HMAC256(sign));
    }


}
