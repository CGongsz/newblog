package com.nov.newblog.beans;

import java.util.List;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 14:01
 * @Version: 1.0
 */
public class Response<T> {
    private String code;
    private String message;
    private Long total;
    private T data;
    private List<T> table;

    public final static String OK = "ok";
    public final static String ERROR = "error";

    public static Response ok() {
        Response<Object> objectResponse = new Response<>();
        objectResponse.setCode(OK);
        return objectResponse;
    }

    public static Response error(String message) {
        Response<Object> objectResponse = new Response<>();
        objectResponse.setCode(ERROR);
        objectResponse.setMessage(message);
        return objectResponse;
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getTable() {
        return table;
    }

    public void setTable(List<T> table) {
        this.table = table;
    }




}
