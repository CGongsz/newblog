package com.nov.newblog.service.user;

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
     * @return
     */
    UserVO register(UserVO userVO);

    Page<UserInfo> findUserList(UserQO userQO);

}
