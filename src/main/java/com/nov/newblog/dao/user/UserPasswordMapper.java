package com.nov.newblog.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nov.newblog.beans.po.LogMessage;
import com.nov.newblog.beans.po.UserPassword;
import org.springframework.stereotype.Repository;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-12 16:46
 * @Version: 1.0
 */
@Repository
public interface UserPasswordMapper extends BaseMapper<UserPassword> {
//    int insert(UserPassword userPassword);
}
