package com.seal.butler;

import com.didispace.swagger.butler.EnableSwaggerButler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/6/24 12:57
 * @description API文档管家
 * @EnableSwaggerButler 开启Swagger Butler功能
 **/
@EnableDiscoveryClient
@EnableSwaggerButler
@SpringBootApplication
public class SpringbootSwaggerbutlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSwaggerbutlerApplication.class, args);
    }

}
