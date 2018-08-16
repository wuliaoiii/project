package com.yangy.common.exception;

import com.yangy.common.enums.ResultCode;

import java.io.Serializable;

/**
 * 自定义异常
 *
 * @author yang yang
 * @email java_yangy@126.com
 * @create 2018/8/1
 * @since 1.0.0
 */
public class MyException extends RuntimeException implements Serializable {

    private int code;
    private String msg;

    public MyException() {
        super();
    }

    public MyException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public MyException(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public MyException(ResultCode enums) {
        super();
        this.code = enums.getCode();
        this.msg = enums.getMsg();
    }

    public Integer getStatus() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}