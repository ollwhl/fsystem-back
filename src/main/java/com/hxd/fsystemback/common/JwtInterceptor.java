package com.hxd.fsystemback.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.hxd.fsystemback.entity.User;
import com.hxd.fsystemback.exception.CustomException;
import com.hxd.fsystemback.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor{
        private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

        @Resource
        private UserService userService;

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws CustomException{
                String token = request.getHeader("token");//从Header中拿token
                System.out.println("token"+token);
                if(StrUtil.isBlank(token)){
                        token = request.getParameter("token");//如果header里没有则从参数里拿
                }
                if(StrUtil.isBlank(token)){
                        String msg = "aaa";
                        //System.out.println(msg);
                        throw new CustomException(msg);//没有Token则抛出异常
                }
                String userId;
                User user;
                try {
                        userId= JWT.decode(token).getAudience().get(0);//解码token 获得userid
                        System.out.println("id: "+userId);
                        user = userService.findUserById(Integer.parseInt(userId));//找出后台中的该id的User
                }catch(Exception e){
                        String errMsg = "token验证失败，请重新登录";
                        log.error(errMsg + ",token: " + token, e);
                        throw new CustomException(errMsg);
                }
                if (user == null){
                        throw new CustomException("用户不存在");
                }
                try {
                        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();//验证password
                        jwtVerifier.verify(token);
                }catch(JWTVerificationException e){
                        throw new CustomException("token验证失败，请重新登录");
                }
                return true;
        }

}
