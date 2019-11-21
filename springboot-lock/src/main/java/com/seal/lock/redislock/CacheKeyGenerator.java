package com.seal.lock.redislock;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/11/21 17:35
 * @description Key 生成策略（接口）
 * 创建一个 CacheKeyGenerator 具体实现由使用者自己去注入
 **/
public interface CacheKeyGenerator {
    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @param pjp PJP
     * @return 缓存KEY
     */
    String getLockKey(ProceedingJoinPoint pjp);

}
