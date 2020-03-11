package com.nov.newblog.controller.user;

import com.nov.newblog.anno.IgnoreLogin;
import com.nov.newblog.beans.Response;
import com.nov.newblog.beans.vo.UserVO;
import com.nov.newblog.dao.UserInfoMapper;
import com.nov.newblog.enums.ExceptionEnum;
import com.nov.newblog.enums.PrefixEnum;
import com.nov.newblog.exception.BlogException;
import com.nov.newblog.service.user.UserService;
import com.nov.newblog.utils.MD5Util;
import com.nov.newblog.utils.RSAUtil;
import com.nov.newblog.utils.Redis;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

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

    @Autowired
    private UserService userService;

    @Value(value = "${privateKey}")
    public String privateKey;

    @Autowired
    private Redis redis;


    @RequestMapping("/backstage/login")
    @IgnoreLogin

    public Response login(@RequestBody UserVO userVO) throws Exception {
        // 私钥解密
        String decrypt = RSAUtil.decrypt(userVO.getPassword(), privateKey);
        UserVO user = userService.selectByAccount(userVO.getAccount());
        // md5验证密码正确性
        if (Objects.isNull(user) || !MD5Util.verify(decrypt, user.getPassword())) {
            request.getSession().removeAttribute(PrefixEnum.LOGIN.name());
            throw new BlogException(ExceptionEnum.NO_EQUAL);
        }

        request.getSession().setAttribute(PrefixEnum.LOGIN.name(), userVO);
        return Response.ok();
    }


}
