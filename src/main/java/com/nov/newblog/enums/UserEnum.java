package com.nov.newblog.enums;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-12 17:19
 * @Version: 1.0
 */
public enum UserEnum {
    ADMIN(0, "管理员"),
    USER(1, "普通用户"),;
    private Integer type;
    private String msg;

    UserEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
