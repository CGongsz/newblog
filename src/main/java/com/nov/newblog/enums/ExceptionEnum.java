package com.nov.newblog.enums;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 13:52
 * @Version: 1.0
 */
public enum ExceptionEnum {
    NO_LOGIN("401", "未登录或登录超时"),
    NO_USER("402", "无此用户"),
    NO_EQUAL("403", "账号或密码不正确"),
    UNKNOWN("405", "未知错误"),
    ERROR_PARAM("406", "参数错误");


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
