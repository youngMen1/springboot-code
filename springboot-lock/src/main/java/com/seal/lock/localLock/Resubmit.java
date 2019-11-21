package com.seal.lock.localLock;

import java.lang.annotation.*;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/11/21 14:59
 * @description 配置注解
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Resubmit {

    /**
     * 延时时间 在延时多久后可以再次提交
     *
     * @return Time unit is one second
     */
    int delaySeconds() default 20;
}
