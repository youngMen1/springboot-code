package com.seal.pool.concurrency;

import java.util.concurrent.*;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/28 15:47
 * 新建一个线程了，只要有三种方式：
 * 1.通过继承Thread类，重写run方法；
 * 2.通过实现Runnable接口；
 * 3.通过实现callable接口这三种方式，下面看具体demo。
 **/
public class CreateThreadDemo {
    public static void main(String[] args) {
        //1.继承Thread
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("继承Thread");
                super.run();
            }
        };
        thread.start();
        //2.实现runable接口
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("实现runable接口");
            }
        });
        thread1.start();
        //3.实现callable接口
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> future = service.submit(new Callable() {
            @Override
            public String call() throws Exception {
                return "通过实现Callable接口";
            }
        });
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
