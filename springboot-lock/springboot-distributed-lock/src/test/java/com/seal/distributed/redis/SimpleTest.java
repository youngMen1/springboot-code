package com.seal.distributed.redis;

import com.seal.distributed.lock.Callback;
import com.seal.distributed.lock.redis.RedisDistributedLockTemplate;
import com.seal.distributed.lock.redis.RedisReentrantLock;
import org.junit.Test;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

/**
 * Created by sunyujia@aliyun.com on 2016/2/24.
 */
public class SimpleTest {

    public static void main(String[] args) throws Exception {
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);//实际应用时可通过spring注入
        RedisReentrantLock lock = new RedisReentrantLock(jedisPool, "订单流水号");
        try {
            if (lock.tryLock(5000L, TimeUnit.MILLISECONDS)) {
                //TODO 获得锁后要做的事
            } else {
                //TODO 获得锁超时后要做的事
            }
        } finally {
            lock.unlock();
        }
    }

    @Test
    public static void test1() {
        // 实际应用时可通过spring注入
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
        // 本类多线程安全,可通过spring注入
        final RedisDistributedLockTemplate template = new RedisDistributedLockTemplate(jedisPool);
        template.execute("订单流水号", 5000, new Callback() {
            @Override
            public Object onGetLock() throws InterruptedException {
                //TODO 获得锁后要做的事
                return null;
            }

            @Override
            public Object onTimeout() throws InterruptedException {
                //TODO 获得锁超时后要做的事
                return null;
            }
        });
    }
}
