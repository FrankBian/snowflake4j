package com.gansuer.project.snowflake.api;

import lombok.Data;

@Data
public class Result<T> {

    private boolean success = false;

    private String errCode;

    private String errMsg;

    private T data;


    public static <T> Result<T> succeedWith(T data) {
        Result<T> res = new Result<T>();
        res.setSuccess(true);
        res.setData(data);
        return res;
    }

    public static <T> Result<T> failedWith(String errCode,String errMsg){
        Result<T> res = new Result<T>();
        res.setSuccess(false);
        res.setErrCode(errCode);
        res.setErrMsg(errMsg);
        return res;
    }
}
