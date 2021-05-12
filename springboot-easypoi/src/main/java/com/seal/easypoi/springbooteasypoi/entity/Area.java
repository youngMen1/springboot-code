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
public class Area {

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("省市编码")
    @Excel(name = "省市编码", orderNum = "0", width = 15)
    private String areaCode;

    @ApiModelProperty("地区名称")
    @Excel(name = "地区名称", orderNum = "1", width = 15)
    private String areaName;

    @ApiModelProperty("地区名称")
    @Excel(name = "级别", orderNum = "2", width = 15)
    private Integer level;

    @Excel(name = "父节点", orderNum = "3", width = 15)
    private String parentCode;



}
