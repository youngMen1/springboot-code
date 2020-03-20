package com.seal.docker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/12 17:04
 * @description
 **/
@RestController
public class TestController {

    @RequestMapping("/hello")
    public String home() {
        return "Hello Docker World";
    }
}
