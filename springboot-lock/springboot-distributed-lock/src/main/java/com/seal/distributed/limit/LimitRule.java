package com.seal.distributed.limit;

import lombok.Data;

/**
 * 限制规则
 *
 * @author fengzhiqiang
 * @date-time 2020/4/24 13:45
 **/
@Data
public class LimitRule {

    /**
     * 单位时间 秒
     */
    private int seconds;

    /**
     * 单位时间内限制的访问次数
     */
    private int limitCount;

    /**
     * 锁的次数
     */
    private int lockCount;

    /**
     * 锁的时长
     * 锁定多少秒,多少秒内无法访问
     */
    private int lockTime;

    public boolean enableLimitLock() {
        return getLockTime() > 0 && getLockCount() > 0;
    }
}
