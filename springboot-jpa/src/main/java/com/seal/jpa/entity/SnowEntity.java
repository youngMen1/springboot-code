package com.seal.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/9 15:25
 * @description 通过@Entity 表明是一个映射的实体类， @Id表明id， @GeneratedValue 字段自动生成
 **/
@Data
@Entity
public class SnowEntity {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false, columnDefinition = "varchar(100) comment '姓名'")
    private String name;
    @Column(nullable = false, columnDefinition = "varchar(100) default '' comment '性别'")
    private String sex;
    @Column(nullable = false, columnDefinition = "varchar(100) default '' comment '地址'")
    private String address;
}
