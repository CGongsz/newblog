package com.nov.newblog.dao;

import com.nov.newblog.beans.po.User;
import org.springframework.stereotype.Repository;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 16:54
 * @Version: 1.0
 */
@Repository
public interface UserMapper {
    User selectByPrimaryKey(Integer id);
}
