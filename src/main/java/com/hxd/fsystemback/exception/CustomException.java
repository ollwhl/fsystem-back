package com.hxd.fsystemback.exception;

import lombok.Data;

@Data
public class CustomException extends Exception {
    private String msg;
    public CustomException(String msg){
        super(msg); // 调用父类 Exception 的构造函数来设置异常信息
        this.msg = msg;
    }

}
