package com.seal.distributed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 分布式锁实现
 * 分布式速率限制器实现
 * 分布式序列实现等
 * SpringBoot 1.5的版本默认采用的连接池技术是jedis  2.0以上版本默认连接池是lettuce,
 * @author fengzhiqiang
 * @date-time 2020/4/24 13:45
 **/
@SpringBootApplication
public class SpringbootDistributedLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDistributedLockApplication.class, args);
    }

}
