package com.seal.distributed.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/24 15:42
 **/
@Slf4j
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;
    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;

    /**
     * 生成key的策略
     *
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        //设置自动key的生成规则，配置springboot的注解，进行方法级别的缓存
        //使用：进行分割，可以更多的显示出层级关系
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(o.getClass().getName());
                stringBuilder.append(":");
                stringBuilder.append(method.getName());
                for (Object object : objects) {
                    stringBuilder.append(":" + String.valueOf(object));
                }
                String key = String.valueOf(stringBuilder);
                log.info("自动生成Redis Key -> [{}]", key);
                return key;
            }
        };
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        //初始化缓存管理器，在这里可以缓存的整体过期时间等，默认么有配置
        log.info("初始化 -> [{}]", "CacheManager RedisCacheManager Start");
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(jedisConnectionFactory);
        return builder.build();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        //设置序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        //配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        RedisSerializer serializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(serializer);//key序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);//value序列化
        redisTemplate.setHashKeySerializer(serializer);//Hash key 序列化
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);//Hash value序列化
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Override
    @Bean
    public CacheErrorHandler errorHandler() {
        //异常处理，当Redis发生异常时，打印日志，但是程序正常运行
        log.info("初始化 -> [{}]", "Redis CacheErrorHandler");
        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object o) {
                log.error("Redis occur handleCacheGetError：key -> [{}]", o, e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object o1) {
                log.error("Redis occur handleCachePutError：key -> [{}]；value -> [{}]", o, o1, e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object o) {
                log.error("Redis occur handleCacheEvictError：key -> [{}]", o, e);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                log.error("Redis occur handleCacheClearError：", e);
            }
        };
        return cacheErrorHandler;
    }

    @Bean
    public JedisPoolConfig setJedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxActive);//最大连接数，连接全部用完，进行等待
        poolConfig.setMinIdle(minIdle); //最小空余数
        poolConfig.setMaxIdle(maxIdle); //最大空余数
        return poolConfig;
    }

    @Bean
    public JedisPool setJedisPool(JedisPoolConfig jedisPoolConfig) {
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
        return jedisPool;
    }
}
