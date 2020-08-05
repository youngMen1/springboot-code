package com.seal.easypoi.springbooteasypoi.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author fengzhiqiang
 * @date 2020/8/5 17:29
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PersonExportDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Excel(name = "姓名", orderNum = "0", width = 15)
    private String name;

    @Excel(name = "用户名", orderNum = "1", width = 15)
    private String username;

    @Excel(name = "手机号码", orderNum = "2", width = 15)
    private String phoneNumber;

    @Excel(name = "人脸图片", orderNum = "3", width = 15, height = 30, type = 2)
    private String imageUrl;
}