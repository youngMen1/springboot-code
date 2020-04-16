package com.seal.rabbitmq.mapper;

import com.seal.rabbitmq.entity.MsgLog;

import java.util.List;

public interface MsgLogMapper {

    /**
     * 插入
     *
     * @param msgLog
     */
    void insert(MsgLog msgLog);

    /**
     * 修改状态
     *
     * @param msgLog
     */
    void updateStatus(MsgLog msgLog);

    /**
     * 下一次重试时间 小于 当前时间和消息投递状态为"0"投递中的
     *
     * @return
     */
    List<MsgLog> selectTimeoutMsg();

    /**
     * 修改重试次数
     *
     * @param msgLog
     */
    void updateTryCount(MsgLog msgLog);

    /**
     * 根据msgId查询
     *
     * @param msgId
     * @return
     */
    MsgLog selectByPrimaryKey(String msgId);

}
