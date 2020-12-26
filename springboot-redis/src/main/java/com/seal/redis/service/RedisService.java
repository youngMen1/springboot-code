package com.seal.redis.service;

import java.util.List;
import java.util.Map;

public interface RedisService {

    /**
     * 根据 redis  的key的集合批量查询对应存在数据
     *
     * @param keys
     * @param useParallel (是否使用并行平行流)是否使用parallel 在没有顺序要求的时候,提高效率,true为表示使用,false 表示不用,默认为true
     * @return
     */
    Map<String, Object> batchQueryByKeys(List<String> keys, Boolean useParallel);

    /**
     * 批量查询
     *
     * @param keys
     * @return
     */
    List<Object> batchFindByKeys(List<String> keys);

    /**
     * 使用管道的方式,批量添加数据
     *
     * @param keyValueMap
     * @param expire      过期时间
     * @return
     */
    List<Object> batchPutInPipelined(Map<String, Object> keyValueMap, long expire);

    /**
     * 批量删除
     * @param list
     */
    void batchDeleteByKeys(List<String> list);
}
