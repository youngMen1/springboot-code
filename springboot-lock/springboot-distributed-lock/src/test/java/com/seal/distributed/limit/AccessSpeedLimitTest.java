package com.seal.distributed.limit;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.JedisPool;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author fengzhiqiang
 * @date-time 2020/4/24 13:59
 **/
public class AccessSpeedLimitTest {

    private JedisPool jedisPool;

    @Before
    public void before() {
        jedisPool = new JedisPool("47.107.152.93", 6379);
    }

    /**
     * 10.0.0.1这个ip每1秒钟最多访问5次if块内代码
     */
    @Test
    public void test1() {
        AccessSpeedLimit accessSpeedLimit = new AccessSpeedLimit(jedisPool);
        SimpleDateFormat sdf = new SimpleDateFormat(" mm:ss");
        while (true) {
            // 10.0.0.1这个ip每1秒钟最多访问5次if块内代码.
            if (accessSpeedLimit.tryAccess("10.0.0.1", 1, 5)) {
                System.out.println("yes" + sdf.format(new Date()));
            } else {
                System.out.println("no" + sdf.format(new Date()));
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 10.0.0.1这个ip每1秒钟最多访问5次if块内代码.1秒超过10次后,锁定2秒,2秒内无法访问
     */
    @Test
    public void test2() {
        LimitRule limitRule = new LimitRule();
        limitRule.setSeconds(1);
        limitRule.setLimitCount(5);
        limitRule.setLockCount(10);
        limitRule.setLockTime(2);
        AccessSpeedLimit accessSpeedLimit = new AccessSpeedLimit(jedisPool);
        SimpleDateFormat sdf = new SimpleDateFormat(" mm:ss");
        while (true) {
            // 10.0.0.1这个ip每1秒钟最多访问5次if块内代码.1秒超过10次后,锁定2秒,2秒内无法访问.
            if (accessSpeedLimit.tryAccess("10.0.0.1", limitRule)) {
                System.out.println("yes" + sdf.format(new Date()));
            } else {
                System.out.println("no" + sdf.format(new Date()));
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
