package com.hxd.fsystemback.common;

import jakarta.annotation.Resource;
import org.aopalliance.intercept.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class WebConfig implements WebMvcConfigurer {
    @Resource
    JwtInterceptor jwtInterceptor;
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer){
        configurer.addPathPrefix("/api",clazz -> clazz.isAnnotationPresent(RestController.class));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //除了在登陆时以外都要拦截所有带/api的请求
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**").excludePathPatterns("/api/user/login");
    }
}
