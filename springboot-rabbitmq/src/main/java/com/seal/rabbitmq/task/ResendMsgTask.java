package com.seal.rabbitmq.task;

import com.seal.rabbitmq.common.Constant;
import com.seal.rabbitmq.mq.producer.MessageHelper;
import com.seal.rabbitmq.entity.MsgLog;
import com.seal.rabbitmq.service.MsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/9/25 14:09
 * @description ResendMsg定时任务重新投递发送失败的消息
 * 说明: 每一条消息都和exchange routingKey绑定, 所有消息重投共用这一个定时任务即可
 **/
@Slf4j
@Component
public class ResendMsgTask {

    @Resource
    private MsgLogService msgLogService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 最大投递次数
     */
    private static final int MAX_TRY_COUNT = 3;

    /**
     * 每30s拉取投递失败的消息, 重新投递
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void resend() {
        log.info("开始执行定时任务(重新投递消息)");
        List<MsgLog> msgLogs = msgLogService.selectTimeoutMsg();
        msgLogs.forEach(msgLog -> {
            String msgId = msgLog.getMsgId();
            if (msgLog.getTryCount() >= MAX_TRY_COUNT) {
                // 如果重试次数大于3次修改消息状态为"2"投递失败
                msgLogService.updateStatus(msgId, Constant.MsgLogStatus.DELIVER_FAIL);
                log.info("超过最大重试次数, 消息投递失败, msgId:{}", msgId);
            } else {
                // 投递次数+1
                msgLogService.updateTryCount(msgId, msgLog.getNextTryTime());
                CorrelationData correlationData = new CorrelationData(msgId);
                // 重新投递
                rabbitTemplate.convertAndSend(msgLog.getExchange(), msgLog.getRoutingKey(), MessageHelper.objToMsg(msgLog.getMsg()), correlationData);
                log.info("第 " + (msgLog.getTryCount() + 1) + " 次重新投递消息");
            }
        });
        log.info("定时任务执行结束(重新投递消息)");
    }

}
