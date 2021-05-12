package com.seal.easypoi.springbooteasypoi.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author fengzhiqiang
 * @date 2021/4/30 11:51
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class ContractDto {

    @NotBlank(message = "到款单号不能为空")
    @Excel(name = "到款单号", orderNum = "0", width = 15)
    private String arrivalNumber;

    @Excel(name = "到款凭证号", orderNum = "1", width = 15)
    private String paymentVoucherNumber;

    @Excel(name = "到款金额", orderNum = "2", width = 15)
    private BigDecimal actualAmount;
}
