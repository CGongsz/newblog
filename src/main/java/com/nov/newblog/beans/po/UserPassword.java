package com.nov.newblog.beans.po;

import java.io.Serializable;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 14:11
 * @Version: 1.0
 */
public class UserPassword implements Serializable {
    private Long id;
    private Long userId;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
