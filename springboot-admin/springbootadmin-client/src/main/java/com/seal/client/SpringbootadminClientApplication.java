package com.seal.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/2 09:16
 * @description SpringBoot Admin客户端
 **/
@EnableEurekaClient
@SpringBootApplication
public class SpringbootadminClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootadminClientApplication.class, args);
    }

}
