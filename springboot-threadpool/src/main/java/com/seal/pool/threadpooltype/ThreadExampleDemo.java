package com.seal.pool.threadpooltype;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/28 12:02
 * 在《阿里巴巴java开发手册》
 * Executors 返回的线程池对象的弊端如下：
 * 1） FixedThreadPool 和 SingleThreadPool：
 * 允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM。
 * 2） CachedThreadPool：
 * 允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM。
 *
 * 中指出了线程资源必须通过线程池提供，
 * 不允许在应用中自行显示的创建线程，这样一方面是线程的创建更加规范，
 * 可以合理控制开辟线程的数量；
 * 另一方面线程的细节管理交给线程池处理，
 * 优化了资源的开销。而线程池不允许使用Executors去创建，而要通过ThreadPoolExecutor方式，
 * 这一方面是由于jdk中Executor框架虽然提供了如
 * newFixedThreadPool()、
 * newSingleThreadExecutor()、
 * newCachedThreadPool()等创建线程池的方法，
 * 但都有其局限性，不够灵活【消耗内存等】；
 * 另外由于前面几种方法内部也是通过ThreadPoolExecutor方式实现，
 * 使用ThreadPoolExecutor有助于大家明确线程池的运行规则，
 * 创建符合自己的业务场景需要的线程池，避免资源耗尽的风险，
 * 所以阿里巴巴java开发规范线程池首选ThreadPoolExcutor。
 **/
public class ThreadExampleDemo {

    private static final ThreadPoolExecutor THREADPOOL = new ThreadPoolExecutor(2, 4, 3,
            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
            new ThreadPoolExecutor.DiscardOldestPolicy());

}
