package com.nov.newblog.enums;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 13:24
 * @Version: 1.0
 */
public enum PrefixEnum {
    LOGIN("登录前缀")
    ;
    private String msg;

    PrefixEnum(String msg) {
        this.msg = msg;
    }
}
