package com.seal.log.model;

import lombok.Data;

import java.util.List;

@Data
public class ExecutionResult {

    /**
     * 错误编码
     */
    private String resultCode;

    /**
     * 是否成功标志
     */
    private boolean flag;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 数据条数
     */
    private int total;

    /**
     * 数据集
     */
    private List<?> data;
}
