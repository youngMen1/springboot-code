package com.seal.rabbitmq.controller;

import com.seal.rabbitmq.common.ServerResponse;
import com.seal.rabbitmq.entity.Mail;
import com.seal.rabbitmq.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/9/3 10:07
 * @description 发送邮件
 **/
@Api(value = "TestController", tags = "发送邮件")
@RestController
@RequestMapping(value = "/api/test/")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("send")
    @ApiOperation(value = "sendMail", notes = "sendMail")
    public ServerResponse sendMail(@RequestBody @Validated Mail mail) {
        return testService.send(mail);
    }
}
