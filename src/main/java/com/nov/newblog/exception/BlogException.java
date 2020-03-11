package com.nov.newblog.exception;

import com.nov.newblog.enums.ExceptionEnum;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 13:51
 * @Version: 1.0
 */
public class BlogException extends RuntimeException{
    private ExceptionEnum exceptionEnum;

    private String msg;

    public BlogException(ExceptionEnum exceptionEnum) {
        msg = exceptionEnum.getMsg();
        this.exceptionEnum = exceptionEnum;
    }

    public void setExceptionEnum(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    public BlogException(ExceptionEnum exceptionEnum, String msg) {
        this.exceptionEnum = exceptionEnum;
        this.msg = msg;
    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
