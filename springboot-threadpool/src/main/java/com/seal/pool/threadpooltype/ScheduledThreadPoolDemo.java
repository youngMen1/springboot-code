package com.seal.pool.threadpooltype;

import com.seal.pool.service.MyThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/28 11:35
 * 延迟连接池
 **/
public class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        // 创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求。
        ExecutorService pool = Executors.newScheduledThreadPool(2);
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        Thread t3 = new MyThread();
        Thread t4 = new MyThread();
        Thread t5 = new MyThread();
        // 将线程放入池中进行执行
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        // 关闭线程池
        pool.shutdown();
    }
}
