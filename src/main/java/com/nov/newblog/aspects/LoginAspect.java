package com.nov.newblog.aspects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nov.newblog.anno.IgnoreLogin;
import com.nov.newblog.enums.ExceptionEnum;
import com.nov.newblog.exception.BlogException;
import com.nov.newblog.utils.CommonUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 13:39
 * @Version: 1.0
 */
@Aspect
@Component
@Order(10)
public class LoginAspect {

    @Pointcut("execution(public * com.nov.newblog.controller..*.*(..))")
    public void login(){};

    @Before("login()")
    public void beforeCut(JoinPoint joinPoint) throws JsonProcessingException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        if (!method.isAnnotationPresent(IgnoreLogin.class)) {
            Object currentUser = CommonUtils.getCurrentUser();
            if (Objects.isNull(currentUser)) {
                throw new BlogException(ExceptionEnum.NO_LOGIN);
            }
        }

    }

}
