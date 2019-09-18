package com.seal.tkmybatis.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: login_log
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "login_log")
public class LoginLog {
    /**
     * id
     *
     * Table:     login_log
     * Column:    id
     * Nullable:  false
     */
    @Id
    private Integer id;

    /**
     * 用户名
     *
     * Table:     login_log
     * Column:    account
     * Nullable:  false
     */
    private String account;

    /**
     * ip地址
     *
     * Table:     login_log
     * Column:    ip
     * Nullable:  false
     */
    private String ip;

    /**
     * 来源(员工系统、会员系统、WEB、小程序)
     *
     * Table:     login_log
     * Column:    source_system
     * Nullable:  false
     */
    @Column(name = "source_system")
    private String sourceSystem;

    /**
     * 创建时间
     *
     * Table:     login_log
     * Column:    create_datetime
     * Nullable:  false
     */
    @Column(name = "create_datetime")
    private Date createDatetime;

    /**
     * 登录类型(登录类型（手机号/邮箱/用户名/员工号）或第三方应用名称（微信 等）)
     *
     * Table:     login_log
     * Column:    identity_type
     * Nullable:  true
     */
    @Column(name = "identity_type")
    private String identityType;

    /**
     * Table:     login_log
     * Column:    uid
     * Nullable:  false
     */
    private String uid;
}