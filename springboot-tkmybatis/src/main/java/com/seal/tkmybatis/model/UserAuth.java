package com.seal.tkmybatis.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: user_auth
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_auth")
public class UserAuth {
    /**
     * id
     *
     * Table:     user_auth
     * Column:    id
     * Nullable:  false
     */
    @Id
    private Integer id;

    /**
     * 用户基础表uid
     *
     * Table:     user_auth
     * Column:    uid
     * Nullable:  true
     */
    private String uid;

    /**
     * 登录类型(登录类型（手机号:PHONE/邮箱:EMAIL//员工号:EMPLOYEE/用户名:PWD）或第三方应用名称（微信:WECHAT 等）)
     *
     * Table:     user_auth
     * Column:    identity_type
     * Nullable:  true
     */
    @Column(name = "identity_type")
    private String identityType;

    /**
     * 标识（13888888888/123@example.com/888888/wentian/或第三方应用的唯一标识微信）
     *
     * Table:     user_auth
     * Column:    identifier
     * Nullable:  true
     */
    private String identifier;

    /**
     * 创建时间
     *
     * Table:     user_auth
     * Column:    create_time
     * Nullable:  true
     */
    @Column(name = "create_time")
    private Date createTime;
}