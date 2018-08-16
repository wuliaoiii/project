package com.yangy.common.enums;

public enum ResultCode {

    OK(200, "SUCCESS"),

    NET_BUSY(500, "NET BUSY"),

    PARAM_ERROR(500, "param is not correct"),

    SAVE_FAIL(0, "添加记录失败"),
    UPDATE_FAIL(1, "修改记录失败"),
    DEL_FAIL(2, "删除记录失败"),


    ;

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
