package com.seal.elasticsearch.service;

import com.seal.elasticsearch.entity.Entity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/****
 *
 * 功能描述: 实现EntityEs
 *
 * @param:  * @param null
 * @return:
 */
public interface EntityRepository extends ElasticsearchRepository<Entity,Long> {

    Entity queryEmployeeById(Long id);


}
