package com.seal.oauth2.authcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/6/24 12:57
 * @description 授权码授权模式
 **/
@SpringBootApplication
public class AuthcodeServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthcodeServerApplication.class, args);
    }

}
