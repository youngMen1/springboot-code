package com.seal.distributed.controller;

import com.seal.distributed.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/24 15:05
 **/
@RestController
@RequestMapping("/api/test/")
public class TestController {

    @Autowired
    TestService testService;

    /**
     * redis 分布式锁
     *
     * @return
     */
    @GetMapping("try")
    public String test() {
        return testService.getTest();
    }




}
