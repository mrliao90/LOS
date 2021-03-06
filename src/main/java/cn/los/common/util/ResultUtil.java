package cn.los.common.util;

import cn.los.common.vo.Result;

public class ResultUtil<T> {

    private Result<T> result;

    public ResultUtil(){
        result=new Result<>();
        result.setSuccess(true);
        result.setMessage("success");
        result.setCode(200);
    }

    public Result<T> setData(T t){
        this.result.setData(t);
        this.result.setCode(200);
        return this.result;
    }
    
    public Result<T> setData2(T t,String token){
        this.result.setData(t);
        this.result.setCode(200);
        this.result.setToken(token);
        return this.result;
    }

    public Result<T> setSuccessMsg(String msg){
        this.result.setSuccess(true);
        this.result.setMessage(msg);
        this.result.setCode(200);
        this.result.setData(null);
        return this.result;
    }

    public Result<T> setData(T t, String msg){
        this.result.setData(t);
        this.result.setCode(200);
        this.result.setMessage(msg);
        return this.result;
    }

    public Result<T> setErrorMsg(String msg){
        this.result.setSuccess(false);
        this.result.setMessage(msg);
        this.result.setCode(500);
        return this.result;
    }

    public Result<T> setErrorMsg(Integer code, String msg){
        this.result.setSuccess(false);
        this.result.setMessage(msg);
        this.result.setCode(code);
        return this.result;
    }

}