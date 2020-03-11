package com.nov.newblog.aspects;

import com.nov.newblog.anno.IgnoreLogin;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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
    public void beforeCut(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        if (!method.isAnnotationPresent(IgnoreLogin.class)) {

        }
    }
}
