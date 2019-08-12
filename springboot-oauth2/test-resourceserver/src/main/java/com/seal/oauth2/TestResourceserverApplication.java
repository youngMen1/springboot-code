package com.seal.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/6/24 12:57
 * @description 资源获取服务
 * @EnableResourceServer开启资源服务
 **/

@EnableResourceServer
@SpringBootApplication
public class TestResourceserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestResourceserverApplication.class, args);
    }


}
