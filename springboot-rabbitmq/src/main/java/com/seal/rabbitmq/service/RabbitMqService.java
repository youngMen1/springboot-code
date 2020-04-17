package com.seal.rabbitmq.service;

import com.seal.rabbitmq.entity.User;

public interface RabbitMqService {
    /**
     * 发送消息
     *
     * @param user
     */
    void sendMessage(User user);
}
