package com.seal.rabbitmq.mq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/9/3 10:07
 * @description 监听器
 **/
@Slf4j
@Component
public class ReceiverListener {
    /**
     * 监听替补队列 来验证死信.
     *
     * @param message the message
     * @param channel the channel
     * @throws IOException the io exception  这里异常需要处理
     */
    @RabbitListener(queues = {"REDIRECT_QUEUE"})
    public void redirect(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        log.info("dead message  10s 后 消费消息 {}", new String(message.getBody()));
    }
}
