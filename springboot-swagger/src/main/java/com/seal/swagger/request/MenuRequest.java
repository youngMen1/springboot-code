package com.seal.swagger.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/13 14:02
 * @description
 **/
@Data
public class MenuRequest {

    @ApiModelProperty(value = "id", example = "123456")
    private String id;

    @ApiModelProperty(value = "姓名", example = "张三")
    private String name;

    @ApiModelProperty(value = "性别", example = "男")
    private String sex;


}
