package com.seal.rabbitmq.service.impl;

import com.seal.rabbitmq.common.ResponseCode;
import com.seal.rabbitmq.common.ServerResponse;
import com.seal.rabbitmq.config.RabbitMqConfig;
import com.seal.rabbitmq.mq.producer.MessageHelper;
import com.seal.rabbitmq.entity.Mail;
import com.seal.rabbitmq.entity.MsgLog;
import com.seal.rabbitmq.mapper.MsgLogMapper;
import com.seal.rabbitmq.service.TestService;
import com.seal.rabbitmq.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Resource
    private MsgLogMapper msgLogMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public ServerResponse testIdempotence() {
        return ServerResponse.success("testIdempotence: success");
    }

    @Override
    public ServerResponse accessLimit() {
        return ServerResponse.success("accessLimit: success");
    }

    /**
     * 生产者发送消息
     * @param mail
     * @return
     */
    @Override
    public ServerResponse send(Mail mail) {
        String msgId = RandomUtil.UUID32();
        mail.setMsgId(msgId);

        // 消息状态为"0"消息投递中
        MsgLog msgLog = new MsgLog(msgId, mail, RabbitMqConfig.MAIL_EXCHANGE_NAME, RabbitMqConfig.MAIL_ROUTING_KEY_NAME);

        // 消息入库
        msgLogMapper.insert(msgLog);

        CorrelationData correlationData = new CorrelationData(msgId);

        // 发送消息
        rabbitTemplate.convertAndSend(RabbitMqConfig.MAIL_EXCHANGE_NAME, RabbitMqConfig.MAIL_ROUTING_KEY_NAME, MessageHelper.objToMsg(mail), correlationData);

        return ServerResponse.success(ResponseCode.MAIL_SEND_SUCCESS.getMsg());
    }

}
