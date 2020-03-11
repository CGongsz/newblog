package com.nov.newblog.controller.user;

import com.nov.newblog.beans.po.User;
import com.nov.newblog.beans.vo.UserVO;
import com.nov.newblog.enums.PrefixEnum;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 13:07
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
@Api("用户模块")
public class UserController {
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/backstage/login")
    public void login(@RequestBody UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        request.getSession().setAttribute(PrefixEnum.LOGIN.name(), user);
        System.out.println(userVO);
    }

    @RequestMapping("/backstage/test")
    public void test() {
        User attribute = (User) request.getSession().getAttribute(PrefixEnum.LOGIN.name());
        System.out.println(attribute);
    }


}
