package com.nov.newblog.aspects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nov.newblog.anno.IgnoreLogin;
import com.nov.newblog.beans.vo.UserVO;
import com.nov.newblog.enums.ExceptionEnum;
import com.nov.newblog.enums.PrefixEnum;
import com.nov.newblog.exception.BlogException;
import com.nov.newblog.utils.CommonUtils;
import com.nov.newblog.utils.MD5Util;
import com.nov.newblog.utils.RSAUtil;
import com.nov.newblog.utils.Redis;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 13:39
 * @Version: 1.0
 */
@Aspect
@Component
@Order(8)
public class LoginAspect {

    @Value(value = "${privateKey}")
    private String privateKey;

    @Autowired
    private Redis redis;

    @Pointcut("execution(public * com.nov.newblog.controller..*.*(..))")
    public void login(){}

    @Before("login()")
    public void beforeCut(JoinPoint joinPoint) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        if (!method.isAnnotationPresent(IgnoreLogin.class)) {
            Object currentUser = CommonUtils.getCurrentUser();
            if (Objects.isNull(currentUser)) {
                throw new BlogException(ExceptionEnum.NO_LOGIN);
            }
            UserVO user = (UserVO) currentUser;
            String account = CommonUtils.getHeaderValue("account");
            String password = CommonUtils.getHeaderValue("password");
            // md5验证密码正确性
            if (!Objects.equals(account, user.getAccount()) ||
                    !Objects.equals(password, user.getPassword())) {
                throw new BlogException(ExceptionEnum.NO_EQUAL);
            } else {
                CommonUtils.getRequest().getSession().setAttribute(PrefixEnum.LOGIN.name(), user);
            }
        }

    }

}
