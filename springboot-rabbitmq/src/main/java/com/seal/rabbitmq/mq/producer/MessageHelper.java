package com.seal.rabbitmq.mq.producer;

import com.seal.rabbitmq.util.JsonUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;

/**
 * 消息持久化
 */
public class MessageHelper {

    public static Message objToMsg(Object obj) {
        if (null == obj) {
            return null;
        }
        Message message = MessageBuilder.withBody(JsonUtil.objToStr(obj).getBytes()).build();
        // 消息持久化 我知道一个方法就是把消息持久化，RabbitMQ中发消息的时候会有个durable参数可以设置，设置为true，就会持久化。
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        message.getMessageProperties().setContentType(MessageProperties.CONTENT_TYPE_JSON);
        return message;
    }


    public static <T> T msgToObj(Message message, Class<T> clazz) {
        if (null == message || null == clazz) {
            return null;
        }
        String str = new String(message.getBody());
        T obj = JsonUtil.strToObj(str, clazz);

        return obj;
    }

}
