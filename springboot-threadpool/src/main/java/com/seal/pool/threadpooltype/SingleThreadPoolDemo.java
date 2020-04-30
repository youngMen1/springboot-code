package com.seal.pool.threadpooltype;

import com.seal.pool.service.MyThread;

import java.util.concurrent.*;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/28 10:17
 * 单任务线程池
 **/
public class SingleThreadPoolDemo {

    private static final ThreadPoolExecutor THREADPOOL = new ThreadPoolExecutor(2, 4, 3,
            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) {
        // 创建一个单线程的线程池。此线程池支持定时以及周期性执行任务的需求。
        ExecutorService pool = Executors.newSingleThreadExecutor();
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
