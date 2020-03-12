package com.nov.newblog.controller.user;

import com.nov.newblog.anno.IgnoreLogin;
import com.nov.newblog.beans.Response;
import com.nov.newblog.beans.qo.UserQO;
import com.nov.newblog.beans.vo.UserVO;
import com.nov.newblog.enums.ExceptionEnum;
import com.nov.newblog.enums.PrefixEnum;
import com.nov.newblog.exception.BlogException;
import com.nov.newblog.service.user.UserService;
import com.nov.newblog.utils.CommonUtils;
import com.nov.newblog.utils.MD5Util;
import com.nov.newblog.utils.RSAUtil;
import com.nov.newblog.utils.Redis;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
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


    @PostMapping("/backstage/login")
    @IgnoreLogin
    @ApiOperation("登录接口，登录成功会将信息放入session")
    public Response login(@RequestBody UserVO userVO) throws Exception {
        // 私钥解密
        String decrypt = RSAUtil.decrypt(userVO.getPassword(), privateKey);

        Object u = redis.get(PrefixEnum.USER.name() + userVO.getAccount());
        if (Objects.equals(u, ExceptionEnum.NO_USER.name())) {
            throw new BlogException(ExceptionEnum.NO_USER);
        }
        UserVO user = null;
        // 如果缓存中不存在用户信息，则从数据库中查询并放入缓存
        if (u == null) {
            user = userService.selectByAccount(userVO.getAccount());
            if (Objects.nonNull(user)) {
                String userStr = CommonUtils.gsonThreadLocal.get().toJson(user);
                redis.set(PrefixEnum.USER.name() + userVO.getAccount(), userStr);
            } else {
                // 不存在的用户，缓存10分钟，防止恶意访问
                redis.set(PrefixEnum.USER.name() + userVO.getAccount(), ExceptionEnum.NO_USER.name(), 600);
            }
        } else {
            user = CommonUtils.gsonThreadLocal.get().fromJson(u.toString(), UserVO.class);
        }

        // md5验证密码正确性
        if (Objects.isNull(user) || !MD5Util.verify(decrypt, user.getPassword())) {
            request.getSession().removeAttribute(PrefixEnum.LOGIN.name());
            throw new BlogException(ExceptionEnum.NO_EQUAL);
        }

        request.getSession().setAttribute(PrefixEnum.LOGIN.name(), userVO);
        return Response.ok();
    }

    @PostMapping("/backstage/register")
    @IgnoreLogin
    @ApiOperation("注册接口")
    public Response register(@RequestBody UserVO userVO) {
        userService.register(userVO);
        return null;
    }

    @PostMapping("/backstage/findUserList")
    @ApiOperation("分页查询用户接口")
    public Response findUserList(@RequestBody UserQO userQO) {
        userService.findUserList(userQO);
        return null;
    }

}
