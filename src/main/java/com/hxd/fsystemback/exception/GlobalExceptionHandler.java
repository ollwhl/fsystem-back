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
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public Result error(HttpServletRequest request,Exception e){
//        log.error("UnKnowError: " + e.getMessage());
//        return Result.error("UnKnowError:" + e.getMessage());
//    }
    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public Result sqlError(HttpServletRequest request,SQLException e){
        return Result.error("SqlError:" + e.getMessage());
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result customError(HttpServletRequest request,CustomException e){
        return Result.error(e.getMsg());
    }

//    @ExceptionHandler(NullPointerException.class)
//    @ResponseBody
//    public void handleNullPointerException(HttpServletRequest request, NullPointerException e) {
//
//    }

    @ExceptionHandler(TransactionException.class)
    @ResponseBody
    public  Result handleTransactionException(HttpServletRequest request, TransactionException e){
        return Result.error("TranslationException");
    }

}
