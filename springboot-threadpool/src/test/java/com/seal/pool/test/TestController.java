package com.seal.pool.test;

import com.seal.pool.common.ThreadPoolManager;
import com.seal.pool.service.MyThread;
import org.junit.Test;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/24 17:55
 **/
public class TestController {

    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            Thread t1 = new MyThread();
            ThreadPoolManager.newInstance().addExecuteTask(t1);
        }

    }
}
