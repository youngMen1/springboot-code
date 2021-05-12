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
 * @date 2021/4/30 18:04
 **/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ArrivalDetail {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("凭证号码")
    @Excel(name = "凭证号码", orderNum = "0", width = 15)
    private String voucherNumber;

    @ApiModelProperty("合同号")
    @Excel(name = "合同号", orderNum = "1", width = 15)
    private Long contractNumber;

    @ApiModelProperty("到款金额")
    private BigDecimal actualAmount;
}

