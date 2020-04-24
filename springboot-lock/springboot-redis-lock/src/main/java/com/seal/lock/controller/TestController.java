package com.seal.lock.controller;

import com.seal.lock.config.R;
import com.seal.lock.limiting.Limit;
import com.seal.lock.localLock.Resubmit;
import com.seal.lock.dto.BaseResponseDataDTO;
import com.seal.lock.dto.UserInfoDTO;
import com.seal.lock.redislock.CacheLock;
import com.seal.lock.redislock.CacheParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/11/21 15:16
 * @description 优雅解决分布式限流和重复提交（分布式锁）
 **/
@Slf4j
@Api(tags = {"重复提交模块"})
@RestController("TestController")
@RequestMapping("/api/lock/")
public class TestController {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    @PostMapping("saveUserInfo")
    @Resubmit(delaySeconds = 10)
    @ApiOperation(value = "本地锁", notes = "借助本地锁解决重复提交问题")
    public R<BaseResponseDataDTO> saveUserInfo(@RequestBody @Validated UserInfoDTO requestDto) {
        BaseResponseDataDTO baseResponseDataDTO = new BaseResponseDataDTO();
        baseResponseDataDTO.setName("seal");
        baseResponseDataDTO.setSex("男");
        baseResponseDataDTO.setAge(10);
        return new R<>(HttpStatus.OK.value(), "success", baseResponseDataDTO);
    }

    @GetMapping
    @CacheLock(prefix = "books")
    @ApiOperation(value = "分布式redis锁", notes = "借助分布式redis锁解决重复提交问题")
    public String query(@CacheParam(name = "token") @RequestParam String token) {
        return "success - " + token;
    }

    @Limit(key = "test", period = 100, count = 10)
    @GetMapping("/testLimiter")
    @ApiOperation(value = "分布式限流", notes = "分布式限流")
    public int testLimiter() {
        // 意味著 100S 内最多允許訪問10次
        return ATOMIC_INTEGER.incrementAndGet();
    }
}
