package com.seal.elasticsearch.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import java.util.List;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2018/10/24 15:32
 * @description TODO
 **/
@Data
@Document(indexName = "region",type = "propertyIds", shards = 1,replicas = 0, refreshInterval = "-1")
public class Region {

    private String id;

    /**
     * region下的propertyId
     */
    private List<String> propertyId;

}
