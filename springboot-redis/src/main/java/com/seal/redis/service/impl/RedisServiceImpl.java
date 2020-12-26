package com.seal.redis.service.impl;

import com.seal.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fengzhiqiang
 * @date 2020/12/26 13:43
 **/
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> batchQueryByKeys(List<String> keys, Boolean useParallel) {
        if (CollectionUtils.isEmpty(keys)) {
            return null;
        }
        if (null == useParallel) {
            useParallel = true;
        }
        List<Object> results = redisTemplate.executePipelined(
                (RedisCallback<Object>) connection -> {
                    StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
                    for (String key : keys) {
                        stringRedisConn.get(key);
                    }
                    return null;
                });
        if (CollectionUtils.isEmpty(results)) {
            return null;
        }
        Map<String, Object> resultMap;
        if (useParallel) {
            Map<String, Object> resultMapOne = Collections.synchronizedMap(new HashMap<>(16));
            keys.parallelStream().forEach(t -> {
                resultMapOne.put(t, results.get(keys.indexOf(t)));
            });
            resultMap = resultMapOne;
        } else {
            Map<String, Object> resultMapTwo = new HashMap<>(16);
            for (String t : keys) {
                resultMapTwo.put(t, results.get(keys.indexOf(t)));
            }
            resultMap = resultMapTwo;
        }
        return resultMap;
    }

    @Override
    public List<Object> batchFindByKeys(List<String> keys) {
        List<Object> results = redisTemplate.executePipelined(
                (RedisCallback<Object>) connection -> {
                    StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
                    for (String key : keys) {
                        stringRedisConn.get(key);
                    }
                    return null;
                });
        return results;
    }

    @Override
    public List<Object> batchPutInPipelined(Map<String, Object> keyValueMap, long expire) {
        List<Object> results = redisTemplate.executePipelined(
                (RedisCallback<Object>) connection -> {
                    StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
                    for (String key : keyValueMap.keySet()) {
                        if (null != keyValueMap.get(key)) {
                            stringRedisConn.set(key, keyValueMap.get(key).toString());
                            stringRedisConn.expire(key, expire);
                        }
                    }
                    return null;
                });
        return results;
    }

    @Override
    public void batchDeleteByKeys(List<String> list) {
        redisTemplate.executePipelined(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                for (String str : list) {
                    operations.delete(str);
                }
                return null;
            }
        });
    }

}
