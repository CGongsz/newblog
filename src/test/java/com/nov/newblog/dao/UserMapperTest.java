package com.nov.newblog.dao;


import com.nov.newblog.beans.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 16:57
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest{
    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectByPrimaryKey() {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);
    }
}