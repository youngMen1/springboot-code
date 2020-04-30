package com.seal.distributed.redis;

import com.seal.distributed.lock.Callback;
import com.seal.distributed.lock.redis.RedisDistributedLockTemplate;
import org.junit.Test;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;


public class RedisReentrantLockTemplateTest {

    @Test
    public void tryTest() throws InterruptedException {
        JedisPool jp = new JedisPool("47.XXX.XXX.93", 6379);
        final RedisDistributedLockTemplate template = new RedisDistributedLockTemplate(jp);

        int size = 100;
        final CountDownLatch startCountDownLatch = new CountDownLatch(1);
        final CountDownLatch endDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            new Thread() {
                public void run() {
                    try {
                        startCountDownLatch.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    final int sleepTime = ThreadLocalRandom.current().nextInt(5) * 1000;
                    template.execute("test", 5000, new Callback() {
                       //TODO 获得锁后要做的事
                        public Object onGetLock() throws InterruptedException {
                            System.out.println(Thread.currentThread().getName() + ":getLock");
                            Thread.currentThread().sleep(sleepTime);
                            System.out.println(Thread.currentThread().getName() + ":sleeped:" + sleepTime);
                            endDownLatch.countDown();
                            return null;
                        }
                        //TODO 获得锁超时后要做的事
                        public Object onTimeout() throws InterruptedException {
                            System.out.println(Thread.currentThread().getName() + ":timeout");
                            endDownLatch.countDown();
                            return null;
                        }
                    });
                }
            }.start();
        }
        // 将count值减1
        startCountDownLatch.countDown();
        // 调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
        endDownLatch.await();
    }
}
