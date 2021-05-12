package com.seal.easypoi.springbooteasypoi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seal.easypoi.springbooteasypoi.entity.Arrival;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author fengzhiqiang
 * @date 2021/4/30 17:23
 **/
@Mapper
public interface ArrivalMapper extends BaseMapper<Arrival> {

    /**
     * 批量新增  到款单信息
     *
     * @param list
     * @return
     */
    int batchInsertMember(List<Arrival> list);
}
