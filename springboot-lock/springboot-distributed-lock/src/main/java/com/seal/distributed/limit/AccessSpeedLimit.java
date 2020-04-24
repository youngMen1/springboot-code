package com.seal.distributed.limit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * 分布式速率限制
 * 例如:限制n秒钟请求x次
 *
 * @author fengzhiqiang
 * @date-time 2020/4/24 13:51
 **/
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Data
public class AccessSpeedLimit {

    private JedisPool jedisPool;

    /**
     * 针对资源key,每seconds秒最多访问maxCount次,超过maxCount次返回false
     *
     * @param key
     * @param seconds
     * @param limitCount
     * @return
     */
    public boolean tryAccess(String key, int seconds, int limitCount) {
        LimitRule limitRule = new LimitRule();
        limitRule.setLimitCount(limitCount);
        limitRule.setSeconds(seconds);
        return tryAccess(key, limitRule);
    }

    /**
     * 针对资源key,每limitRule.seconds秒最多访问limitRule.limitCount,
     * 超过limitCount次返回false
     * 超过lockCount 锁定lockTime
     *
     * @param key
     * @param limitRule
     * @return
     */
    public boolean tryAccess(String key, LimitRule limitRule) {
        String newKey = "Limit:" + key;
        Jedis jedis = null;
        long count = -1;
        try {
            jedis = jedisPool.getResource();
            List<String> keys = new ArrayList<>();
            keys.add(newKey);
            List<String> args = new ArrayList<>();
            args.add(Math.max(limitRule.getLimitCount(), limitRule.getLockCount()) + "");
            args.add(limitRule.getSeconds() + "");
            args.add(limitRule.getLockCount() + "");
            args.add(limitRule.getLockTime() + "");
            count = Long.parseLong(jedis.eval(buildLuaScript(limitRule), keys, args) + "");
            log.info("count:{}", count);
            return count <= limitRule.getLimitCount();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    private String buildLuaScript(LimitRule limitRule) {
        StringBuilder lua = new StringBuilder();
        lua.append("\nlocal c");
        lua.append("\nc = redis.call('get',KEYS[1])");
        lua.append("\nif c and tonumber(c) > tonumber(ARGV[1]) then");
        lua.append("\nreturn c;");
        lua.append("\nend");
        lua.append("\nc = redis.call('incr',KEYS[1])");
        lua.append("\nif tonumber(c) == 1 then");
        lua.append("\nredis.call('expire',KEYS[1],ARGV[2])");
        lua.append("\nend");
        if (limitRule.enableLimitLock()) {
            lua.append("\nif tonumber(c) > tonumber(ARGV[3]) then");
            lua.append("\nredis.call('expire',KEYS[1],ARGV[4])");
            lua.append("\nend");
        }
        lua.append("\nreturn c;");
        // log.info("buildLuaScript:{}", lua.toString());
        return lua.toString();
    }
}
