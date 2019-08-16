package com.seal.log.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "seal_systemlog")
public class SystemLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    /**
     * 操作IP
     */
    @Column(name = "requestip")
    private String requestip;

    /**
     * 操作类型 1 操作记录 2异常记录
     */
    @Column(name = "type")
    private String type;

    /**
     * 操作人ID
     */
    @Column(name = "userid")
    private String userid;

    /**
     * 操作描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 操作时间
     */
    @Column(name = "actiondate")
    private Date actiondate;

    /**
     * 异常code
     */
    @Column(name = "exceptioncode")
    private String exceptioncode;

    /**
     * 异常详情
     */
    @Column(name = "exceptiondetail")
    private String exceptiondetail;

    /**
     * 请求方法
     */
    @Column(name = "actionmethod")
    private String actionmethod;

    /**
     * 请求参数
     */
    @Column(name = "params")
    private String params;

}
