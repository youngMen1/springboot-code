package com.seal.swagger.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/13 13:54
 * @description 响应信息主体
 **/
@ApiModel("统一返回格式说明：R")
@ToString
public class R<T> implements Serializable {
    private static final int SUCCESS = 0;
    private static final int FAIL = 1;
    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    @ApiModelProperty(value = "接口返回错误时的提示信息")
    private String msg = "success";

    @Getter
    @Setter
    @ApiModelProperty(value = "接口返回状态码，1：失败，0成功")
    private int code = SUCCESS;

    @Getter
    @Setter
    @ApiModelProperty(value = "接口返回数据内容")
    private T data;

    public R() {
        super();
    }

    public R(T data) {
        super();
        this.data = data;
    }

    public R(T data, String msg) {
        super();
        this.code = FAIL;
        this.data = data;
        this.msg = msg;
    }

    public R(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = FAIL;
    }

    public R(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
}
