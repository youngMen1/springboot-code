package com.seal.redis.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis内存淘汰策略测试
 */
public class RedisMaxmemoryPolicyTest {


    private RedisTemplate<String, Object> redisTemplate;

    @Before
    public void before() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("192.168.244.137");
        redisStandaloneConfiguration.setPort(6379);

        LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder().build();
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration, lettuceClientConfiguration);
        lettuceConnectionFactory.afterPropertiesSet();

        redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.afterPropertiesSet();

    }

    @Test
    public void test() {
        List<String> list = new ArrayList<>(redisTemplate.keys("*"));
        list.sort(Comparator.comparing(Integer::valueOf));
        for (String key : list) {
            System.out.println(key);
        }
    }

    @Test
    public void noevicationTest() {
        // flush db
        redisTemplate.delete(redisTemplate.keys("*"));
        // 1k
        byte[] bytes = new byte[1024];

        int i = 0;
        while (true) {
            try {
                redisTemplate.opsForValue().set(String.valueOf(i), bytes);
                System.out.println(i++);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

    @Test
    public void volatileTest() {
        //flush db
        redisTemplate.delete(redisTemplate.keys("*"));
        //1k
        byte[] bytes = new byte[1024];

        int i = 0;
        for (; i < 100; i++) {
            redisTemplate.opsForValue().set(String.valueOf(i), bytes, 10, TimeUnit.MINUTES);
            System.out.println(i);
        }

        for (; i < 200; i++) {
            redisTemplate.opsForValue().set(String.valueOf(i), bytes);
            System.out.println(i);
        }
    }

    @Test
    public void allkeysTest() throws InterruptedException {
        //flush db
        redisTemplate.delete(redisTemplate.keys("*"));
        //1k
        byte[] bytes = new byte[1024];

        int i = 0;
        for (; i < 100; i++) {
            redisTemplate.opsForValue().set(String.valueOf(i), bytes);
            System.out.println(i);
        }

        Thread.sleep(1000);
        for (; i < 200; i++) {
            redisTemplate.opsForValue().set(String.valueOf(i), bytes);
            System.out.println(i);
        }
    }

    @Test
    public void volatileTtlTest() {
        //flush db
        redisTemplate.delete(redisTemplate.keys("*"));
        //1k
        byte[] bytes = new byte[1024];

        for (int i = 0; i < 1000; i++) {
            redisTemplate.opsForValue().set(String.valueOf(i), bytes, i + 1, TimeUnit.MINUTES);
            System.out.println(i);
        }
    }
}
