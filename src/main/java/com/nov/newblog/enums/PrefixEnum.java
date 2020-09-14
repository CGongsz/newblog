package com.nov.newblog.enums;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 13:24
 * @Version: 1.0
 */
public enum PrefixEnum {
    LOGIN("USE:LOGIN", "登录前缀"),
    USERNAME("USE:USERNAME", "用户名"),
    USER_LIST("USE:USER_LIST", "用户列表")
    ;
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    PrefixEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
