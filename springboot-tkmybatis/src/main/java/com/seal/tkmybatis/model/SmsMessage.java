package com.seal.tkmybatis.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: sms_message
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sms_message")
public class SmsMessage {
    /**
     * id
     *
     * Table:     sms_message
     * Column:    id
     * Nullable:  false
     */
    @Id
    private Integer id;

    /**
     * 手机号
     *
     * Table:     sms_message
     * Column:    phone
     * Nullable:  true
     */
    private String phone;

    /**
     * 验证码
     *
     * Table:     sms_message
     * Column:    code
     * Nullable:  true
     */
    private String code;

    /**
     * 年份
     *
     * Table:     sms_message
     * Column:    year
     * Nullable:  true
     */
    private String year;

    /**
     * 月份
     *
     * Table:     sms_message
     * Column:    month
     * Nullable:  true
     */
    private String month;

    /**
     * 消息
     *
     * Table:     sms_message
     * Column:    message
     * Nullable:  true
     */
    private String message;

    /**
     * 阿里云返回信息
     *
     * Table:     sms_message
     * Column:    return_message
     * Nullable:  true
     */
    @Column(name = "return_message")
    private String returnMessage;

    /**
     * 创建时间
     *
     * Table:     sms_message
     * Column:    create_time
     * Nullable:  true
     */
    @Column(name = "create_time")
    private Date createTime;
}