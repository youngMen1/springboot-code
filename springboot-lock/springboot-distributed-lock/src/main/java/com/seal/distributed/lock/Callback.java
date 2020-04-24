package com.seal.distributed.lock;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/24 14:27
 **/
public interface Callback {

    /**
     * 获得锁后要做的事
     * @return
     * @throws InterruptedException
     */
    Object onGetLock() throws InterruptedException;

    /**
     * 获得锁超时后要做的事
     * @return
     * @throws InterruptedException
     */
    Object onTimeout() throws InterruptedException;

}
