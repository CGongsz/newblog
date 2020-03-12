package com.nov.newblog.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nov.newblog.beans.po.UserInfo;
import com.nov.newblog.beans.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 16:54
 * @Version: 1.0
 */
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    UserInfo selectByPrimaryKey(Integer id);

    UserVO selectByAccount(@Param("account") String account);
}
