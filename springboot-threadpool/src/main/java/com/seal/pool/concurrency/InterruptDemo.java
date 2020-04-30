package com.seal.pool.concurrency;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/28 15:45
 * isInterrupted（）来感知其他线程对其自身的中断操作，从而做出响应。另外，同样可以调用Thread的静态方法
 * interrupted（）对当前线程进行中断操作，该方法会清除中断标志位。
 * **需要注意的是，当抛出InterruptedException时候，会清除中断标志位，也就是说在调用isInterrupted会返回false。
 **/
public class InterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        // sleepThread睡眠1000ms
        final Thread sleepThread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };
        // busyThread一直执行死循环
        Thread busyThread = new Thread() {
            @Override
            public void run() {
                while (true) ;
            }
        };
        sleepThread.start();
        busyThread.start();
        sleepThread.interrupt();
        busyThread.interrupt();
        while (sleepThread.isInterrupted()) ;
        System.out.println("sleepThread isInterrupted: " + sleepThread.isInterrupted());
        System.out.println("busyThread isInterrupted: " + busyThread.isInterrupted());
    }
}
