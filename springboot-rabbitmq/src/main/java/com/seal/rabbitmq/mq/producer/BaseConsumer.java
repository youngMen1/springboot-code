package com.seal.rabbitmq.mq.producer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/9/25 14:09
 * @description 代理模式
 **/
public interface BaseConsumer {

    void consume(Message message, Channel channel);

}
