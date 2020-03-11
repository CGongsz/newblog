package com.nov.newblog.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nov.newblog.beans.po.User;
import com.nov.newblog.enums.PrefixEnum;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 13:59
 * @Version: 1.0
 * 公用工具类
 */
public class CommonUtils {
    public static Object getCurrentUser() throws JsonProcessingException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        Object attribute = request.getSession().getAttribute(PrefixEnum.LOGIN.name());

        return attribute;
    }
}
