package com.seal.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SpringBoot整合spring security
 * @author fengzhiqiang
 * @date-time 2020/4/7-14:20
 **/
@SpringBootApplication
public class SpringbootSpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSpringSecurityApplication.class, args);
    }

}
