package com.nov.newblog.dao;

import com.nov.newblog.beans.po.LogMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 17:15
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogMessageMapperTest {
    @Autowired
    private LogMessageMapper logMessageMapper;


    @Test
    public void insert() {
        LogMessage logMessage = new LogMessage();
        logMessage.setOperation("test");
        logMessage.setReason("test");
        logMessage.setIpAddress("127.0.0.1");
        logMessage.setSuccess(0);
        logMessage.setDate(new Date());
        int insert = logMessageMapper.insert(logMessage);
        System.out.println(insert);
    }
}