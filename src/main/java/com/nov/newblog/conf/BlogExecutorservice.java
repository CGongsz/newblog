package com.nov.newblog.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 15:48
 * @Version: 1.0
 */
@Configuration
public class BlogExecutorservice {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,
                30, 30, TimeUnit.SECONDS, new ArrayBlockingQueue(1000),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        return threadPoolExecutor;
    }
}
