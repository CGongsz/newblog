package com.nov.newblog.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nov.newblog.anno.PageQuery;
import com.nov.newblog.beans.po.UserInfo;
import com.nov.newblog.beans.po.UserPassword;
import com.nov.newblog.beans.qo.UserQO;
import com.nov.newblog.beans.vo.UserVO;
import com.nov.newblog.dao.user.UserInfoMapper;
import com.nov.newblog.dao.user.UserPasswordMapper;
import com.nov.newblog.enums.ExceptionEnum;
import com.nov.newblog.enums.PrefixEnum;
import com.nov.newblog.enums.UserEnum;
import com.nov.newblog.exception.BlogException;
import com.nov.newblog.service.user.UserService;
import com.nov.newblog.utils.CommonUtils;
import com.nov.newblog.utils.MD5Util;
import com.nov.newblog.utils.RSAUtil;
import com.nov.newblog.utils.Redis;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;

import static com.nov.newblog.enums.ExceptionEnum.ERROR_PARAM;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 20:44
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserPasswordMapper userPasswordMapper;

    @Value(value = "${privateKey}")
    public String privateKey;

    @Autowired
    private Redis redis;

    private final static Long TEN_MINUTES = 600L;

    private final static Long ZORE = 0L;


    @Override
    public UserVO selectByAccount(String account) {
        return userInfoMapper.selectByAccount(account);
    }

    @Override
    public void login(UserVO userVO) throws Exception {
        // 私钥解密
        String decrypt = RSAUtil.decrypt(userVO.getPassword(), privateKey);

        Object u = redis.get(PrefixEnum.USER.name() + userVO.getAccount());
        if (Objects.equals(u, ExceptionEnum.NO_USER.name())) {
            throw new BlogException(ExceptionEnum.NO_USER);
        }

        UserVO user = null;
        // 如果缓存中不存在用户信息，则从数据库中查询并放入缓存
        if (u == null) {
            user = selectByAccount(userVO.getAccount());
            if (Objects.nonNull(user)) {
                String userStr = CommonUtils.gsonThreadLocal.get().toJson(user);
                redis.set(PrefixEnum.USER.name() + userVO.getAccount(), userStr);
            } else {
                // 不存在的用户，缓存10分钟，防止恶意访问
                redis.set(PrefixEnum.USER.name() + userVO.getAccount(), ExceptionEnum.NO_USER.name(), TEN_MINUTES);
            }
        } else {
            user = CommonUtils.gsonThreadLocal.get().fromJson(u.toString(), UserVO.class);
        }

        // md5验证密码正确性
        if (Objects.isNull(user) || !MD5Util.verify(decrypt, user.getPassword())) {
            CommonUtils.getRequest().getSession().removeAttribute(PrefixEnum.LOGIN.name());
            throw new BlogException(ExceptionEnum.NO_EQUAL);
        }
        // 将用户设置到分布式session中
        CommonUtils.getRequest().getSession().setAttribute(PrefixEnum.LOGIN.name(), userVO);
    }

    @Override
    public void exit() {
        // 直接清除session数据
        CommonUtils.getRequest().getSession().removeAttribute(PrefixEnum.LOGIN.name());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserVO userVO) throws Exception {
        checkParam(userVO);
        // 私钥解密
        String decrypt = RSAUtil.decrypt(userVO.getPassword(), privateKey);
        if (StringUtils.isEmpty(decrypt)) {
            throw new BlogException(ExceptionEnum.SIGN_WRONG);
        }

        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userVO, userInfo);
        userInfo.setCreateBy(userVO.getAccount());
        userInfo.setCreateDate(new Date());
        userInfo.setUpdateBy(userVO.getAccount());
        userInfo.setUpdateDate(new Date());
        userInfo.setUserType(UserEnum.USER.getType());
        userInfo.setAvatar("");

        userInfoMapper.insert(userInfo);
        userVO.setId(userInfo.getId());


        UserPassword userPassword = new UserPassword();
        userPassword.setUserId(userInfo.getId());
        // MD5加盐处理密码
        userPassword.setPassword(MD5Util.generate(decrypt));
        userPassword.setCreateBy(userVO.getAccount());
        userPassword.setCreateDate(new Date());
        userPassword.setUpdateBy(userVO.getAccount());
        userPassword.setUpdateDate(new Date());

        userPasswordMapper.insert(userPassword);

        // 事务成功后执行
        TransactionSynchronizationManager
                .registerSynchronization(new TransactionSynchronizationAdapter() {
                                             @Override
                                             public void afterCommit() {
                                                 // 防止redis中存在了该用户key
                                                 redis.expire(PrefixEnum.USER + userVO.getAccount(), ZORE);
                                             }
                                         }
                );
    }

    @Override
    public IPage findUserList(@PageQuery UserQO userQO) {
        QueryWrapper queryWrapper = CommonUtils.queryWrapperThreadLocal.get();
        IPage iPage = CommonUtils.pageThreadLocal.get();
        userInfoMapper.selectPage(iPage, queryWrapper);
        return iPage;
    }

    private void checkParam(UserVO userVO) {
        if (StringUtils.isEmpty(userVO.getUsername())) {
            throw new BlogException(ERROR_PARAM, "用户名不能为空");
        }
        if (StringUtils.isEmpty(userVO.getEmail())) {
            throw new BlogException(ERROR_PARAM, "注册邮箱不能为空");
        }

        if (StringUtils.isEmpty(userVO.getPassword())) {
            throw new BlogException(ERROR_PARAM, "用户密码不能为空");
        }

    }
}
