package com.seal.lock.redislock;

import java.lang.annotation.*;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/11/21 17:42
 * @description 锁的参数
 * 上一篇中给说过 key 的生成规则是自己定义的，如果通过表达式语法自己得去写解析规则还是比较麻烦的，所以依旧是用注解的方式
 **/
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {

    /**
     * 字段名称
     *
     * @return String
     */
    String name() default "";
}
