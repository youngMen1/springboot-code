package com.seal.swagger.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/13 13:57
 * @description
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "房间摘要")
public class TravellerInfoDto {

    @ApiModelProperty(value = "名")
    private String firstName;

    @ApiModelProperty(value = "姓")
    private String lastName;

    @ApiModelProperty(value = "特殊要求")
    private String specialRequest;

}
