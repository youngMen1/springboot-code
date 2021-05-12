package com.seal.easypoi.springbooteasypoi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author fengzhiqiang
 * @date 2021/4/30 18:09
 **/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ContractDetail {
    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("合同号")
    @Excel(name = "合同号", orderNum = "0", width = 15)
    private String contractNumber;

    @ApiModelProperty("合同金额")
    @Excel(name = "合同金额", orderNum = "1", width = 15)
    private BigDecimal actualAmount;
}
