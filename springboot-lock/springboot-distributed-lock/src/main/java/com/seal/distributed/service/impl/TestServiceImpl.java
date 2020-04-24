package com.seal.distributed.service.impl;

import com.seal.distributed.lock.Callback;
import com.seal.distributed.lock.redis.RedisDistributedLockTemplate;
import com.seal.distributed.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/24 15:06
 **/
@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public String getTest() {
        RedisDistributedLockTemplate template = new RedisDistributedLockTemplate(jedisPool);
        // 订单流水号
        String orderNo = "123456789";
        // 获取锁超时时间为5秒
        String result = (String) template.execute(orderNo, 5000, new Callback() {
            @Override
            public Object onGetLock() {
                //TODO 获得锁后要做的事
                return "获得锁后要做的事";
            }

            @Override
            public Object onTimeout() {
                //TODO 获得锁超时后要做的事
                return "获得锁超时后要做的事";
            }
        });
        return result;
    }

}
