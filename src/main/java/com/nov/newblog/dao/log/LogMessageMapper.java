package com.nov.newblog.dao.log;

import com.nov.newblog.beans.po.LogMessage;
import org.springframework.stereotype.Repository;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 16:54
 * @Version: 1.0
 */
@Repository
public interface LogMessageMapper {
    LogMessage selectByPrimaryKey(Integer id);

    int insert(LogMessage logMessage);
}
