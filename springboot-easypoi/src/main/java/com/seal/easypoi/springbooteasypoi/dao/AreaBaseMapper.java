package com.seal.easypoi.springbooteasypoi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seal.easypoi.springbooteasypoi.entity.Area;
import com.seal.easypoi.springbooteasypoi.entity.AreaBase;

import java.util.List;

/**
 * @author fengzhiqiang
 * @date 2021/5/6 10:10
 **/
public interface AreaBaseMapper extends BaseMapper<AreaBase> {

    /**
     * 批量新增  到款单明细表
     *
     * @param list
     * @return
     */
    int batchInsertMember(List<AreaBase> list);

}
