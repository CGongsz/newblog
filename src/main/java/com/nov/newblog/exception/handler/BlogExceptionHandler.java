package com.nov.newblog.exception.handler;

import com.nov.newblog.beans.Response;
import com.nov.newblog.exception.BlogException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 15:38
 * @Version: 1.0
 */
@ControllerAdvice(basePackages = "com.nov.newblog")
public class BlogExceptionHandler {
    @ExceptionHandler(BlogException.class)
    @ResponseBody
    public Response errorResult(BlogException e){
        Response response = new Response();
        response.setCode(e.getExceptionEnum().getCode());
        response.setMessage(e.getExceptionEnum().getMsg());
        return response;
    }


}
