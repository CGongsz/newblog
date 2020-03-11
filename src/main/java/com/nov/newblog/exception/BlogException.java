package com.nov.newblog.exception;

import com.nov.newblog.enums.ExceptionEnum;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 13:51
 * @Version: 1.0
 */
public class BlogException extends RuntimeException{
    private ExceptionEnum exceptionEnum;

    public BlogException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.exceptionEnum = exceptionEnum;
    }

    public void setExceptionEnum(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }
}
