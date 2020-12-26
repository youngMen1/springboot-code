package com.seal.redis.controller;

import com.seal.redis.entity.Person;
import com.seal.redis.service.RedisService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @author fengzhiqiang
 * @date 2020/12/26 14:01
 **/
@Slf4j
@RequestMapping("/api/test/")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @PostMapping("redisPipelineSet")
    @ApiOperation(value = "redis的管道 pipeline 添加数据测试")
    public void redisPipelineSet() {
        log.info("redis test 开始");
        // 开始时间
        long start = System.currentTimeMillis();

        List<String> list = new ArrayList<>();

        Map<String, Object> map = new HashMap<>(16);

        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setName("冯之前");
            person.setAge("10");
            String uuid = UUID.randomUUID().toString();
            list.add(uuid);
            map.put(uuid, person);
        }
        // 添加
        redisService.batchPutInPipelined(map, 10);

        // 查询
        redisService.batchQueryByKeys(list, false);

        // 结束时间
        long end = System.currentTimeMillis();
        log.info("运行时间：" + (end - start));
    }

    @PostMapping("redisPipelineGet")
    @ApiOperation(value = "redis的管道 pipeline批量 GET数据测试")
    public void redisPipelineGet() {
        log.info("redis test 开始");
        // 开始时间
        long start = System.currentTimeMillis();

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String uuid = UUID.randomUUID().toString();
            list.add(uuid);
        }
        // 查询
        redisService.batchQueryByKeys(list, false);

        // 结束时间
        long end = System.currentTimeMillis();
        log.info("运行时间：" + (end - start));
    }

    @PostMapping("redisPipelineDelete")
    @ApiOperation(value = "redis的管道 pipeline批量 delete数据测试")
    public void redisPipelineDelete() {
        log.info("redis test 开始");
        // 开始时间
        long start = System.currentTimeMillis();

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String uuid = UUID.randomUUID().toString();
            list.add(uuid);
        }
        // 查询
        redisService.batchDeleteByKeys(list);

        // 结束时间
        long end = System.currentTimeMillis();
        log.info("运行时间：" + (end - start));
    }


}
