package com.seal.rabbitmq.mq.producer;

import com.rabbitmq.client.Channel;
import com.seal.rabbitmq.common.Constant;
import com.seal.rabbitmq.entity.MsgLog;
import com.seal.rabbitmq.service.MsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import java.lang.reflect.Proxy;
import java.util.Map;
/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/9/25 14:09
 * @description 动态代理模式抽离公共代码
 **/
@Slf4j
public class BaseConsumerProxy {

    private Object target;

    private MsgLogService msgLogService;

    public BaseConsumerProxy(Object target, MsgLogService msgLogService) {
        this.target = target;
        this.msgLogService = msgLogService;
    }

    /**
     * 1.保证消费幂等性,
     * 2.发送邮件,
     * 3.更新消息状态, 手动ack
     *
     * @return
     */
    public Object getProxy() {
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class[] interfaces = target.getClass().getInterfaces();

        Object proxy = Proxy.newProxyInstance(classLoader, interfaces, (proxy1, method, args) -> {
            Message message = (Message) args[0];
            Channel channel = (Channel) args[1];

            String correlationId = getCorrelationId(message);

            // 1.消费幂等性, 防止消息被重复消费
            if (isConsumed(correlationId)) {
                log.info("重复消费, correlationId: {}", correlationId);
                return null;
            }
            MessageProperties properties = message.getMessageProperties();
            long tag = properties.getDeliveryTag();

            try {
                // 真正消费的业务逻辑
                Object result = method.invoke(target, args);
                // 3.更新消息状态为"3"已消费
                msgLogService.updateStatus(correlationId, Constant.MsgLogStatus.CONSUMED_SUCCESS);
                // 消费确认 手动ack
                channel.basicAck(tag, false);
                return result;
            } catch (Exception e) {
                log.error("getProxy error", e);
                // 拒绝一条或几条收到的消息
                channel.basicNack(tag, false, true);
                return null;
            }
        });
        return proxy;
    }

    /**
     * 获取CorrelationId
     *
     * @param message
     * @return
     */
    private String getCorrelationId(Message message) {
        String correlationId = null;

        MessageProperties properties = message.getMessageProperties();
        Map<String, Object> headers = properties.getHeaders();
        for (Map.Entry entry : headers.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if (key.equals("spring_returned_message_correlation")) {
                correlationId = value;
            }
        }
        return correlationId;
    }

    /**
     * 消息是否已被消费
     *
     * @param correlationId
     * @return
     */
    private boolean isConsumed(String correlationId) {
        MsgLog msgLog = msgLogService.selectByMsgId(correlationId);
        if (null == msgLog || msgLog.getStatus().equals(Constant.MsgLogStatus.CONSUMED_SUCCESS)) {
            return true;
        }
        return false;
    }

}
