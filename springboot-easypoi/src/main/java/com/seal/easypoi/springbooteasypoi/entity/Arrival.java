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
 * @date 2021/4/30 17:20
 **/

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Arrival {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("到款单号")
    @Excel(name = "到款单号", orderNum = "0", width = 15)
    private String arrivalNumber;

    @ApiModelProperty("到款凭证号")
    @Excel(name = "到款凭证号", orderNum = "1", width = 15)
    private Long paymentVoucherNumber;

    @ApiModelProperty("到款金额")
    @Excel(name = "到款金额", orderNum = "2", width = 15)
    private BigDecimal actualAmount;
}
