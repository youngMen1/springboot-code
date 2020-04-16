package com.seal.rabbitmq.mq.producer;

import com.rabbitmq.client.Channel;
import com.seal.rabbitmq.entity.Mail;
import com.seal.rabbitmq.exception.ServiceException;
import com.seal.rabbitmq.util.JsonUtil;
import com.seal.rabbitmq.util.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/9/25 14:09
 * @description 发送邮件
 **/
@Component
@Slf4j
public class MailConsumer implements BaseConsumer {

    @Autowired
    private MailUtil mailUtil;

    @Override
    public void consume(Message message, Channel channel) {
        Mail mail = MessageHelper.msgToObj(message, Mail.class);
        log.info("收到消息:{}", JsonUtil.objToStr(mail));
        // 发送邮件
        boolean success = mailUtil.send(mail);
        if (!success) {
            throw new ServiceException("send mail error");
        }
    }

}
