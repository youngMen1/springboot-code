package com.seal.tkmybatis.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: users
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    /**
     * id
     *
     * Table:     users
     * Column:    id
     * Nullable:  false
     */
    @Id
    private Integer id;

    /**
     * 关联user_auth表
     *
     * Table:     users
     * Column:    uid
     * Nullable:  true
     */
    private String uid;

    /**
     * 安科id
     *
     * Table:     users
     * Column:    anke_id
     * Nullable:  true
     */
    @Column(name = "anke_id")
    private String ankeId;

    /**
     * 手机号
     *
     * Table:     users
     * Column:    phone
     * Nullable:  true
     */
    private String phone;

    /**
     * 举荐人
     *
     * Table:     users
     * Column:    parent_id
     * Nullable:  true
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 同一用户，对同一个微信开放平台下的不同应用，unionid是相同的。
     *
     * Table:     users
     * Column:    union_id
     * Nullable:  true
     */
    @Column(name = "union_id")
    private String unionId;

    /**
     * 微信用户名
     *
     * Table:     users
     * Column:    nick_name
     * Nullable:  true
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 性别 0：未知、1：男、2：女
     *
     * Table:     users
     * Column:    gender
     * Nullable:  true
     */
    private String gender;

    /**
     * 城市
     *
     * Table:     users
     * Column:    city
     * Nullable:  true
     */
    private String city;

    /**
     * 省份
     *
     * Table:     users
     * Column:    province
     * Nullable:  true
     */
    private String province;

    /**
     * 国家
     *
     * Table:     users
     * Column:    country
     * Nullable:  true
     */
    private String country;

    /**
     * 头像地址
     *
     * Table:     users
     * Column:    avatar_url
     * Nullable:  true
     */
    @Column(name = "avatar_url")
    private String avatarUrl;

    /**
     * 账户来源
     *
     * Table:     users
     * Column:    source
     * Nullable:  true
     */
    private String source;

    /**
     * 创建时间
     *
     * Table:     users
     * Column:    create_datetime
     * Nullable:  true
     */
    @Column(name = "create_datetime")
    private Date createDatetime;

    /**
     * '状态 1:可用 0:不可用'
     *
     * Table:     users
     * Column:    status
     * Nullable:  true
     */
    private String status;

    /**
     * 微信登录是否绑定(默认为0,绑定为1)
     *
     * Table:     users
     * Column:    weixin_binding
     * Nullable:  true
     */
    @Column(name = "weixin_binding")
    private String weixinBinding;

    /**
     * 员工号登录是否绑定(0:未绑定 1:绑定)
     *
     * Table:     users
     * Column:    staff_binding
     * Nullable:  true
     */
    @Column(name = "staff_binding")
    private String staffBinding;

    /**
     * 手机登录是否绑定(0:未绑定 1:已绑定)
     *
     * Table:     users
     * Column:    phone_binding
     * Nullable:  true
     */
    @Column(name = "phone_binding")
    private String phoneBinding;

    /**
     * Table:     users
     * Column:    password
     * Nullable:  true
     */
    private String password;

    /**
     * 修改时间
     *
     * Table:     users
     * Column:    update_time
     * Nullable:  true
     */
    @Column(name = "update_time")
    private Date updateTime;
}