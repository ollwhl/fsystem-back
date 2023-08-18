package com.hxd.fsystemback.common;

import lombok.Data;

@Data
public class Result {
    private static final String SUCCESS = "0";
    private static final String ERROR = "-1";
    private String code;
    private String msg;
    private Object data;

    public static Result success(){
        Result result =new Result();
        result.setCode(SUCCESS);
        return result;
    }
    public static Result success(Object data){
        Result resault = new Result();
        resault.setCode(SUCCESS);
        resault.setData(data);
        return resault;
    }
    public static Result error(String msg){
        Result result =new Result();
        result.setCode(ERROR);
        result.setMsg(msg);
        return result;
    }
}
