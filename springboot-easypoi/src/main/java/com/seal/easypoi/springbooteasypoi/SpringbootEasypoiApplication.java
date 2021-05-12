package com.seal.easypoi.springbooteasypoi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.seal.easypoi.springbooteasypoi.dao")
@ComponentScan(basePackages = "com.seal.easypoi.springbooteasypoi")
public class SpringbootEasypoiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootEasypoiApplication.class, args);
    }

}
