package com.seal.apollo.apolloclient;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ConfigurationProperties使用方式
 *
 * @author fengzhiqiang
 * @date 2020/7/11 15:01
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "redis.cache")
public class SampleRedisConfig {
    private int expireSeconds;
    private int commandTimeout;
}
