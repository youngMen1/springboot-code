package com.seal.rabbitmq.mq.consumer;

import com.rabbitmq.client.Channel;
import com.seal.rabbitmq.config.RabbitMqConfig;
import com.seal.rabbitmq.mq.producer.BaseConsumer;
import com.seal.rabbitmq.mq.producer.BaseConsumerProxy;
import com.seal.rabbitmq.mq.producer.MailConsumer;
import com.seal.rabbitmq.service.MsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/9/3 10:07
 * @description 监听消费消息, 发送邮件
 **/
@Slf4j
@Component
public class MailListener {

    @Autowired
    private MailConsumer mailConsumer;

    @Autowired
    private MsgLogService msgLogService;

    /**
     * 消费消息
     * @param message
     * @param channel
     */
    @RabbitListener(queues = RabbitMqConfig.MAIL_QUEUE_NAME)
    public void consume(Message message, Channel channel) {
        BaseConsumerProxy baseConsumerProxy = new BaseConsumerProxy(mailConsumer, msgLogService);
        // 保证消费幂等性、更新消息状态为"3"已消费、消费确认 手动ack
        BaseConsumer proxy = (BaseConsumer) baseConsumerProxy.getProxy();
        if (null != proxy) {
            // 2.发送邮件
            proxy.consume(message, channel);
        }
    }
}
