package com.seal.easypoi.springbooteasypoi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seal.easypoi.springbooteasypoi.entity.ArrivalDetail;
import com.seal.easypoi.springbooteasypoi.entity.BankArea;

import java.util.List;

/**
 * @author fengzhiqiang
 * @date 2021/5/6 10:11
 **/
public interface BankAreaMapper extends BaseMapper<BankArea> {
    /**
     * 批量新增  到款单明细表
     *
     * @param list
     * @return
     */
    int batchInsertMember(List<BankArea> list);
}
