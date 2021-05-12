package com.seal.easypoi.springbooteasypoi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author fengzhiqiang
 * @date 2021/4/30 17:20
 **/

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BankArea {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("联行号")
    @Excel(name = "联行号", orderNum = "0", width = 15)
    private String unionNumber;

    @ApiModelProperty("开户银行全称")
    @Excel(name = "开户银行全称", orderNum = "1", width = 15)
    private String bankName;

}
