package com.nov.newblog.service.user.impl;

import com.nov.newblog.beans.po.UserInfo;
import com.nov.newblog.beans.vo.UserVO;
import com.nov.newblog.dao.UserInfoMapper;
import com.nov.newblog.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
