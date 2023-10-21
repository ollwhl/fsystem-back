package com.hxd.fsystemback.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hxd.fsystemback.common.Result;

import java.sql.SQLException;


@ControllerAdvice(basePackages = "com.hxd.fsystemback.controller")
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(HttpServletRequest request, Exception e) {
        if (e instanceof SQLException) {
            log.error("SQL Error: " + e.getMessage());
            return Result.error("数据库错误:" + e.getMessage());
        } else if (e instanceof CustomException) {
            log.error("Custom Error: " + e.getMessage());
            return Result.error("错误:" + e.getMessage());
        } else {
            log.error("Unknow Error: " + e.getMessage());
            return Result.error("未知错误:" + e.getMessage());
        }
    }

}
