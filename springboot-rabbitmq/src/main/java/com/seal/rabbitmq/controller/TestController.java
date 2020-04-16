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
 * @description 测试案例
 **/
@Api(value = "TestController", tags = "测试案例")
@RestController
@RequestMapping(value = "/api/test/")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    /**
     * 案例一、保证消息100%投递成功并被消费
     * 发送邮件
     *
     * @param mail
     * @return
     */
    @PostMapping("send")
    @ApiOperation(value = "sendMail", notes = "发送邮件")
    public ServerResponse sendMail(@RequestBody @Validated Mail mail) {
        return testService.send(mail);
    }


}
