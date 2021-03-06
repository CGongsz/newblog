package com.nov.newblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.nov.newblog.dao")
public class NewblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewblogApplication.class, args);
    }

}
