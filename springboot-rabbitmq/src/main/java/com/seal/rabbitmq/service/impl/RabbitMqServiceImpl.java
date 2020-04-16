package com.seal.rabbitmq.service.impl;

import com.seal.rabbitmq.service.RabbitMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/16-14:43
 **/
@Slf4j
@Service
public class RabbitMqServiceImpl implements RabbitMqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


}
