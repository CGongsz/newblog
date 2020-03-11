package com.nov.newblog.aspects;

import com.nov.newblog.beans.Response;
import com.nov.newblog.beans.po.LogMessage;
import com.nov.newblog.utils.CommonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 15:55
 * @Version: 1.0
 */
@Aspect
@Component
@Order(9)
public class LogAspect {
    private Logger loger = LoggerFactory.getLogger(LogAspect.class);
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Pointcut("execution(public * com.nov.newblog.controller..*.*(..))")
    public void log() {
    }

    ;

    @Around("log()")
    public Object logCut(ProceedingJoinPoint joinPoint) {
        StringBuilder content = new StringBuilder();
        loger.info("执行方法 " + joinPoint.getSignature().getName());
        // 初始化日志数据
        LogMessage logMessage = new LogMessage();
        // 获取操作的参数
        Object[] args = joinPoint.getArgs();

        // 写入操作时间
        logMessage.setDate(new Date());
        // 写入操作名
        logMessage.setOperation(joinPoint.getSignature().getName());
        // 获取ip
        logMessage.setIpAddress(CommonUtils.getIpAddr());

        Response response = null;
        try {
            response = (Response) joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logMessage.setSuccess(1);
            logMessage.setReason(throwable.getMessage());
        }

        // 写入操作结果
        if (Objects.isNull(logMessage.getSuccess())) {
            logMessage.setSuccess(0);
        }

        if (Objects.isNull(logMessage.getReason())) {
            Optional.ofNullable(response)
                    .ifPresent(e -> logMessage.setReason(e.getMessage()));
        }

        loger.info(logMessage.toString());
        // 返回操作的原本结果
        return response;
    }
}
