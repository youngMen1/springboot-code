package com.seal.pool.threadpooltype;

import com.seal.pool.service.MyThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/28 11:15
 * 可变尺寸线程池
 * 可缓存线程池：
 * 线程数无限制,
 * 有空闲线程则复用空闲线程，若无空闲线程则新建线程
 * 一定程序减少频繁创建/销毁线程，减少系统开销
 **/
public class CachedThreadPoolDemo {

    public static void main(String[] args) {
        /**
         * 创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，那么就会回收部分空闲（60秒不执行任务）的线程，
         * 当任务数增加时，此线程池又可以智能的添加新线程来处理任务。
         * 此线程池不会对线程池大小做限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。
         */
        ExecutorService pool = Executors.newCachedThreadPool();
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
