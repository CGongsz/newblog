package com.nov.newblog.beans.po;

import java.io.Serializable;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 14:11
 * @Version: 1.0
 */
public class UserPassword implements Serializable {
    private Integer id;
    private Integer userId;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
