package com.nov.newblog.aspects;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nov.newblog.anno.PageQuery;
import com.nov.newblog.beans.qo.BaseQuery;
import com.nov.newblog.utils.CommonUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-12 14:44
 * @Version: 1.0
 */
@Aspect
@Component
@Order(11)
public class PageQueryAspect {
    private final static String LIKE = "like";
    private final static String EQ = "eq";
    private final static String LT = "lt";
    private final static String GT = "gt";

    @Pointcut("execution(public * com.nov.newblog.service..*.*(..))")
    public void pageQuery(){}

    @Before("pageQuery()")
    public void beforeCut(JoinPoint joinPoint) {
        Object[] params = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Annotation[][] annotations = method.getParameterAnnotations();

        for (int i = 0; i < annotations.length; i++) {
            Object param = params[i];
            Annotation[] paramAnn = annotations[i];

            //参数为空，直接下一个参数
            if(param == null || paramAnn.length == 0){
                continue;
            }
            for (Annotation annotation : paramAnn) {
                //这里判断当前注解是否为PageQuery.class
                if(annotation.annotationType().equals(PageQuery.class) && params[i] instanceof BaseQuery){
                    BaseQuery baseQuery = (BaseQuery) params[i];
                    setQuery(baseQuery);
                    break;
                }
            }
        }
    }

    /**
     * 防止内存泄漏
     * @param joinPoint
     */
    @After("pageQuery()")
    public void afterCut(JoinPoint joinPoint) {
        CommonUtils.queryWrapperThreadLocal.remove();
        CommonUtils.gsonThreadLocal.remove();
        CommonUtils.pageThreadLocal.remove();
    }

    // 设置查询条件
    private void setQuery(BaseQuery baseQuery) {
        QueryWrapper queryWrapper = CommonUtils.queryWrapperThreadLocal.get();
        if (!CollectionUtils.isEmpty(baseQuery.getQueryMap())) {
            // 字段查询条件
            baseQuery.getQueryMap().forEach((k, v) -> {
                if (Objects.equals(k.trim(), LIKE)) {
                    v.forEach((field, value) -> {
                        queryWrapper.like(field, value);
                    });
                } else if (Objects.equals(k.trim(), EQ)) {
                    v.forEach((field, value) -> {
                        queryWrapper.eq(field, value);
                    });
                } else if (Objects.equals(k.trim(), LT)) {
                    v.forEach((field, value) -> {
                        queryWrapper.lt(field, value);
                    });
                } else if (Objects.equals(k.trim(), GT)) {
                    v.forEach((field, value) -> {
                        queryWrapper.gt(field, value);
                    });
                }
            });
        }

        // 设置分页数据
        if (Objects.nonNull(baseQuery.getPageNum())) {
            CommonUtils.pageThreadLocal.get().setCurrent(baseQuery.getPageNum());
        }

        if (Objects.nonNull(baseQuery.getPageSize())) {
            CommonUtils.pageThreadLocal.get().setSize(baseQuery.getPageSize());
        }
    }
}
