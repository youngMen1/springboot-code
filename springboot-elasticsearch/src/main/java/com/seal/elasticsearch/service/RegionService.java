package com.seal.elasticsearch.service;

import com.seal.elasticsearch.entity.Region;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2018/10/24 15:35
 * @description TODO
 **/
public interface RegionService extends ElasticsearchRepository<Region,String> {

    Region queryRegionById(String id);
}
