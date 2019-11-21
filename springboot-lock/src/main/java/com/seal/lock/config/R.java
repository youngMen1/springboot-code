package com.seal.lock.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/11/21 15:37
 * @description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> {
    private Integer code;
    private String message;
    private T data;
}
