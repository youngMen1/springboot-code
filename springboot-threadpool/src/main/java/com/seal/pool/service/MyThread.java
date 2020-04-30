package com.seal.pool.service;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/24 17:54
 **/
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "正在执行… …");
    }
}
