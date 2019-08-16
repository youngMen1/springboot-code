package com.seal.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/6/24 12:57
 * @description springboot整合mongodb
 **/
@EnableMongoRepositories(basePackages = {"com.seal.*"})
@SpringBootApplication
public class SpringbootMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMongodbApplication.class, args);
    }

}
