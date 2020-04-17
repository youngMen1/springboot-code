package com.seal.rabbitmq.service.impl;

import com.seal.rabbitmq.entity.User;
import com.seal.rabbitmq.service.RabbitMqService;
import com.seal.rabbitmq.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/16-14:43
 **/
@Slf4j
@Service
public class RabbitMqServiceImpl implements RabbitMqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    public void sendMessage(User user) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        /**
         * 声明消息处理器  这个对消息进行处理  可以设置一些参数
         * 对消息进行一些定制化处理   我们这里  来设置消息的编码  以及消息的过期时间
         * 因为在.net 以及其他版本过期时间不一致   这里的时间毫秒值 为字符串
         */
        MessagePostProcessor messagePostProcessor = message -> {
            MessageProperties messageProperties = message.getMessageProperties();
            // 设置编码
            messageProperties.setContentEncoding("utf-8");
            // 设置过期时间10*1000毫秒
            messageProperties.setExpiration("10000");
            return message;
        };
        // 向DL_QUEUE 发送消息  10*1000毫秒后过期 形成死信
        rabbitTemplate.convertAndSend("DL_EXCHANGE", "DL_KEY", JsonUtil.objToStr(user), messagePostProcessor, correlationData);
    }
}
