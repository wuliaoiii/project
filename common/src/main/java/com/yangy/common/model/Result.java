package com.yangy.common.model;

import com.yangy.common.enums.ResultCode;

/**
 * 返回信息对象
 *
 * @author yang yang
 * @email java_yangy@126.com
 * @create 2018/8/1
 * @since 1.0.0
 */
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result ok(T data) {
        Result<T> result = new Result<T>();
        result.setCode(ResultCode.OK.getCode());
        result.setMsg(ResultCode.OK.getMsg());
        result.setData(data);
        return result;
    }

    public Result error(ResultCode errorCode) {
        Result<String> result = new Result<String>();
        result.setCode(errorCode.getCode());
        result.setMsg(errorCode.getMsg());
        result.setData("");
        return result;
    }

    public Result error(int code, String msg) {
        Result<String> result = new Result<String>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData("");
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}