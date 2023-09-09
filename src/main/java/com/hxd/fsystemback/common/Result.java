package com.hxd.fsystemback.common;

import lombok.Data;

@Data
public class Result {
    private static final String SUCCESS = "0";
    private static final String ERROR = "-1";
    private String code;//返回状态码
    private String msg;
    private Object data;

    public static Result success(){//result succeess function name
        Result result =new Result();
        result.setCode(SUCCESS);
        return result;
    }
    @AutoLog
    public static Result success(Object data){
        Result result = new Result();
        result.setCode(SUCCESS);
        result.setData(data);
        return result;
    }
    public static Result error(String msg){
        Result result =new Result();
        result.setCode(ERROR);
        result.setMsg(msg);
        return result;
    }
}
