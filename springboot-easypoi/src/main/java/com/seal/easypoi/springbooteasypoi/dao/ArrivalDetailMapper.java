package com.seal.easypoi.springbooteasypoi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seal.easypoi.springbooteasypoi.entity.ArrivalDetail;

import java.util.List;

/**
 * @author fengzhiqiang
 * @date 2021/4/30 18:07
 **/
public interface ArrivalDetailMapper extends BaseMapper<ArrivalDetail> {
    /**
     * 批量新增  到款单明细表
     *
     * @param list
     * @return
     */
    int batchInsertMember(List<ArrivalDetail> list);
}
