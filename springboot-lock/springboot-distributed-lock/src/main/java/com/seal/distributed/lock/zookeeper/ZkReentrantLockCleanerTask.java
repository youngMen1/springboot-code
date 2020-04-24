package com.seal.distributed.lock.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/24 15:05
 **/
@Slf4j
public class ZkReentrantLockCleanerTask extends TimerTask {

    private CuratorFramework client;

    private Timer timer;

    /**
     * 检查周期
     */
    private long period = 5000;
    /**
     * Curator RetryPolicy maxRetries
     */
    private int maxRetries = 3;
    /**
     * Curator RetryPolicy baseSleepTimeMs
     */
    private final int baseSleepTimeMs = 1000;

    public ZkReentrantLockCleanerTask(String zookeeperAddress) {
        try {
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries);
            client = CuratorFrameworkFactory.newClient(zookeeperAddress, retryPolicy);
            client.start();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } catch (Throwable ex) {
            ex.printStackTrace();
            log.error(ex.getMessage(), ex);
        }
    }

    public void start() {
        timer.schedule(this, 0, period);
    }

    private boolean isEmpty(List<String> list) {
        return list == null || list.isEmpty();
    }


    @Override
    public void run() {
        try {
            List<String> childrenPaths = this.client.getChildren().forPath(ZkReentrantLock.ROOT_PATH);
            for (String path : childrenPaths) {
                cleanNode(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cleanNode(String path) {
        try {
            if (isEmpty(this.client.getChildren().forPath(path))) {
                // 利用存在子节点无法删除和zk的原子性这两个特性.
                this.client.delete().forPath(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
