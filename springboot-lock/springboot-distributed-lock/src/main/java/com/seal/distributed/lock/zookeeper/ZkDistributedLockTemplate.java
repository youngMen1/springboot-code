package com.seal.distributed.lock.zookeeper;


import com.seal.distributed.lock.Callback;
import com.seal.distributed.lock.DistributedLockTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

import java.util.concurrent.TimeUnit;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/24 15:05
 **/
@Slf4j
public class ZkDistributedLockTemplate implements DistributedLockTemplate {

    private CuratorFramework client;

    public ZkDistributedLockTemplate(CuratorFramework client) {
        this.client = client;
    }


    private boolean tryLock(ZkReentrantLock distributedReentrantLock, Long timeout) throws Exception {
        return distributedReentrantLock.tryLock(timeout, TimeUnit.MILLISECONDS);
    }

    @Override
    public Object execute(String lockId, int timeout, Callback callback) {
        ZkReentrantLock distributedReentrantLock = null;
        boolean getLock = false;
        try {
            distributedReentrantLock = new ZkReentrantLock(client, lockId);
            if (tryLock(distributedReentrantLock, new Long(timeout))) {
                getLock = true;
                return callback.onGetLock();
            } else {
                return callback.onTimeout();
            }
        } catch (InterruptedException ex) {
            log.error(ex.getMessage(), ex);
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (getLock) {
                distributedReentrantLock.unlock();
            }
        }
        return null;
    }
}
