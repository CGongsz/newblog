package com.nov.newblog.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nov.newblog.anno.PageQuery;
import com.nov.newblog.beans.po.UserInfo;
import com.nov.newblog.beans.qo.UserQO;
import com.nov.newblog.beans.vo.UserVO;
import com.nov.newblog.dao.user.UserInfoMapper;
import com.nov.newblog.exception.BlogException;
import com.nov.newblog.service.user.UserService;
import com.nov.newblog.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    @Override
    public UserVO selectByAccount(String account) {
        return userInfoMapper.selectByAccount(account);
    }

    @Override
    public UserVO register(UserVO userVO) {
        checkParam(userVO);
        return null;
    }

    @Override
    public Page<UserInfo> findUserList(@PageQuery UserQO userQO) {
        CommonUtils.pageNumThreadLocal.get();
        QueryWrapper queryWrapper = CommonUtils.queryWrapperThreadLocal.get();
        IPage iPage = CommonUtils.pageThreadLocal.get();
        userInfoMapper.selectPage(iPage, queryWrapper);

        return null;
    }

    private void checkParam(UserVO userVO) {
        if (StringUtils.isEmpty(userVO.getUsername())) {
            throw new BlogException(ERROR_PARAM, "用户名不能为空");
        }
        if (StringUtils.isEmpty(userVO.getEmail())) {
            throw new BlogException(ERROR_PARAM, "注册邮箱不能为空");
        }
        
    }
}
