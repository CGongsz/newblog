package com.nov.newblog.controller.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nov.newblog.anno.IgnoreLogin;
import com.nov.newblog.beans.Response;
import com.nov.newblog.beans.qo.UserQO;
import com.nov.newblog.beans.vo.UserVO;

import com.nov.newblog.service.user.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    private UserService userService;



    @PostMapping("/backstage/login")
    @IgnoreLogin
    @ApiOperation("登录接口，登录成功会将信息放入session")
    public Response login(@RequestBody UserVO userVO) throws Exception {
        userService.login(userVO);
        return Response.ok();
    }

    @PostMapping("/backstage/register")
    @IgnoreLogin
    @ApiOperation("注册接口")
    public Response register(@RequestBody UserVO userVO) throws Exception {
        userService.register(userVO);
        return Response.ok().setData(userVO);
    }

    @PostMapping("/backstage/findUserList")
    @ApiOperation("分页查询用户接口")
    public Response findUserList(@RequestBody UserQO userQO) {
        IPage userList = userService.findUserList(userQO);
        return Response.ok().setData(userList);
    }

}
