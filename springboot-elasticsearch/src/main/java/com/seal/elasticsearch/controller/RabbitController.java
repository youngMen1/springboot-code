package com.seal.elasticsearch.controller;

import com.seal.elasticsearch.entity.HotelSearchEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2018/10/25 18:10
 * @description TODO
 **/
@RequestMapping
@RestController
@Api("RabbitController")
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("send")
    @ApiOperation(value = "send")
    public String send() throws IOException {
        HotelSearchEntity hotelSearchMessage = new HotelSearchEntity();
        hotelSearchMessage.setRegionId("444");
        // rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE_NAME, RabbitConfig.UE_ROUTE_KEY, JsonUtils.serialize(hotelSearchMessage));
        return "success";
    }


}
