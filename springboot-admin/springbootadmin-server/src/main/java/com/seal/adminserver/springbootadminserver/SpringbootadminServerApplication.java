package com.seal.adminserver.springbootadminserver;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/2 09:16
 * @description SpringBoot Admin监控SpringCloud微服务
 **/
@EnableEurekaClient
@EnableAdminServer
@SpringBootApplication
public class SpringbootadminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootadminServerApplication.class, args);
    }

}
