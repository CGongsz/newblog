package com.nov.newblog.enums;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 13:52
 * @Version: 1.0
 */
public enum ExceptionEnum {
    NO_LOGIN("401", "未登录或登录超时");


    private String code;
    private String msg;

    ExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
