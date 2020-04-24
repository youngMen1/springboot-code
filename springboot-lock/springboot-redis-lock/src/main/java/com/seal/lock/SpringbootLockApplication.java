package com.seal.lock;

import com.seal.lock.redislock.CacheKeyGenerator;
import com.seal.lock.redislock.LockKeyGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/6/24 12:57
 * @description 幂等问题 8种方案解决重复提交
 **/
@SpringBootApplication
public class SpringbootLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLockApplication.class, args);
    }

    @Bean
    public CacheKeyGenerator cacheKeyGenerator() {
        return new LockKeyGenerator();
    }

}
