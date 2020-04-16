package com.seal.rabbitmq.service;

import com.seal.rabbitmq.common.ServerResponse;
import com.seal.rabbitmq.entity.Mail;

public interface TestService {

    ServerResponse testIdempotence();

    ServerResponse accessLimit();

    /**
     * 生产者发送消息
     * @param mail
     * @return
     */
    ServerResponse send(Mail mail);
}
