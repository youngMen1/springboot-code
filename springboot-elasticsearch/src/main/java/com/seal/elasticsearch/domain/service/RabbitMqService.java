package com.seal.elasticsearch.domain.service;

import com.rabbitmq.client.Channel;
import com.seal.elasticsearch.content.RabbitConfig;
import com.seal.elasticsearch.entity.HotelSearchEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2018/10/26 12:52
 * @description TODO
 **/
@Component
@Slf4j
public class RabbitMqService {

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receiveRegionSearchRequest(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        log.info("------------11111111111111111111111");
        // 拿到请求的数据
        // HotelSearchEntity hotelSearchMessage = JsonUtils.deserialize(message, HotelSearchEntity.class);
        HotelSearchEntity hotelSearchMessage = null;
        log.info("------------" + hotelSearchMessage);
        boolean type = true;
        // TODO 再根据消费的状态进行业务处理
        if (type) {
            log.info("开始消费" + hotelSearchMessage);
            //手动消费成功
            channel.basicAck(tag, false);
            log.info("消费成功" + hotelSearchMessage);
            log.debug("");
        } else {
            //手动消费失败，消息不回发
            channel.basicNack(tag, false, false);
            log.error("");
        }

    }
}
