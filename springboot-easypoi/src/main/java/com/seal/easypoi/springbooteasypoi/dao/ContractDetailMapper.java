package com.seal.easypoi.springbooteasypoi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seal.easypoi.springbooteasypoi.entity.Arrival;
import com.seal.easypoi.springbooteasypoi.entity.ContractDetail;

import java.util.List;

/**
 * @author fengzhiqiang
 * @date 2021/4/30 18:09
 **/
public interface ContractDetailMapper extends BaseMapper<ContractDetail> {
    /**
     * 批量新增  合同明细表
     *
     * @param list
     * @return
     */
    int batchInsertMember(List<ContractDetail> list);
}
