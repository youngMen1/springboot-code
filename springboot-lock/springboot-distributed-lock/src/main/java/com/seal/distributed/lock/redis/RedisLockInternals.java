package com.seal.distributed.lock.redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/24 14:29
 **/
@Slf4j
public class RedisLockInternals {

    private JedisPool jedisPool;

    /**
     * 重试等待时间
     */
    private int retryAwait = 300;

    /**
     * 锁定超时间
     */
    private int lockTimeout = 2000;


    RedisLockInternals(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public String tryRedisLock(String lockId, long time, TimeUnit unit) {
        final long startMillis = System.currentTimeMillis();
        final Long millisToWait = (unit != null) ? unit.toMillis(time) : null;
        String lockValue = null;
        // 轮询
        while (true) {
            lockValue = createRedisKey(lockId);
            if (lockValue != null) {
                break;
            }
            if (System.currentTimeMillis() - startMillis - retryAwait > millisToWait) {
                break;
            }
            /** 模拟延时用LockSupport.parkNanos
             * LockSupport中的park() 和 unpark() 的作用分别是阻塞线程和解除阻塞线程，而且park()和unpark()不会遇到“Thread.suspend 和 Thread.resume所可能引发的死锁”问题。因为park() 和 unpark()有许可的存在；调用 park() 的线程和另一个试图将其 unpark() 的线程之间的竞争将保持活性。实现原理是利用java的unsafe接口调用本地方法实现。
             * Sleep（）是单纯的在指定时间内让当前正在执行的线程暂停执行，但不会释放“锁标志”，使当前线程进入阻塞状态，在指定时间内不会执行。
             */
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(retryAwait));
        }
        return lockValue;
    }

    private String createRedisKey(String lockId) {
        Jedis jedis = null;
        try {
            String value = lockId + randomId(1);
            jedis = jedisPool.getResource();
            String luaScript = ""
                    + "\nlocal r = tonumber(redis.call('SETNX', KEYS[1],ARGV[1]));"
                    + "\nredis.call('PEXPIRE',KEYS[1],ARGV[2]);"
                    + "\nreturn r";
            List<String> keys = new ArrayList<>();
            keys.add(lockId);
            List<String> args = new ArrayList<>();
            args.add(value);
            args.add(lockTimeout + "");
            Long ret = (Long) jedis.eval(luaScript, keys, args);
            if (new Long(1).equals(ret)) {
                return value;
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public void unlockRedisLock(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String luaScript = ""
                    + "\nlocal v = redis.call('GET', KEYS[1]);"
                    + "\nlocal r= 0;"
                    + "\nif v == ARGV[1] then"
                    + "\nr =redis.call('DEL',KEYS[1]);"
                    + "\nend"
                    + "\nreturn r";
            List<String> keys = new ArrayList<>();
            keys.add(key);
            List<String> args = new ArrayList<>();
            args.add(value);
            Object r = jedis.eval(luaScript, keys, args);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    private final static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
            'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            'Z'};

    private String randomId(int size) {
        char[] cs = new char[size];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = digits[ThreadLocalRandom.current().nextInt(digits.length)];
        }
        return new String(cs);
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(300));
        System.out.println(System.currentTimeMillis());
    }
}
