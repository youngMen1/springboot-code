package com.seal.rabbitmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/6/24 12:57
 * @description springboot整合rabbitmq
 **/
@MapperScan("com.seal.rabbitmq.mapper")
@SpringBootApplication
public class SpringbootRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitmqApplication.class, args);
    }

}
