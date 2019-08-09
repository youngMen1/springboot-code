package com.seal.jpa.dao;

import com.seal.jpa.entity.SnowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/9 15:27
 * @description
 **/
public interface SnowDao extends JpaRepository<SnowEntity, Integer> {
}
