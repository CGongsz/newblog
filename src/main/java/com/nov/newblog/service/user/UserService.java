package com.nov.newblog.service.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nov.newblog.beans.po.UserInfo;
import com.nov.newblog.beans.qo.UserQO;
import com.nov.newblog.beans.vo.UserVO;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 20:42
 * @Version: 1.0
 */
public interface UserService {
    /**
     * 根据账号查询用户
     * @param account
     * @return
     */
    UserVO selectByAccount(String account);

    /**
     * 注册账号
     * @param userVO
     */
    void register(UserVO userVO) throws Exception;

    IPage findUserList(UserQO userQO);

    /**
     * 登录
     * @param userVO
     */
    void login(UserVO userVO) throws Exception;
}
