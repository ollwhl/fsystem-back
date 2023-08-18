package com.hxd.fsystemback.exception;

import lombok.Data;

@Data
public class CustomException extends Throwable {
    private String msg;
    public CustomException(String msg){
        this.msg=msg;
    }

}
