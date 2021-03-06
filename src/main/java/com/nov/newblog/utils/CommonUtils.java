package com.nov.newblog.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.nov.newblog.enums.PrefixEnum;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 13:59
 * @Version: 1.0
 * 公用工具类
 */
public class CommonUtils {

    public static ThreadLocal<Gson> gsonThreadLocal = new ThreadLocal<Gson>(){
        @Override
        protected Gson initialValue() {
            return new Gson();
        }
    };
    public static ThreadLocal<QueryWrapper> queryWrapperThreadLocal = new ThreadLocal<QueryWrapper>(){
        @Override
        protected QueryWrapper initialValue() {
            return new QueryWrapper();
        }
    };
    public static ThreadLocal<IPage> pageThreadLocal = new ThreadLocal<IPage>(){
        @Override
        protected IPage initialValue() {
            return new Page(1, 10);
        }
    };


    /**
     * 获取当前登录人
     * @return
     */
    public static Object getCurrentUser() {
        HttpServletRequest request = getRequest();
        Object attribute = request.getSession().getAttribute(PrefixEnum.LOGIN.getCode());

        return attribute;
    }

    /**
     * 获取header里指定的值
     * @param headerName
     * @return
     */
    public static String getHeaderValue(String headerName) {
        HttpServletRequest request = getRequest();
        String header = request.getHeader(headerName);
        return header;
    }

    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     */
    public static String getIpAddr() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
