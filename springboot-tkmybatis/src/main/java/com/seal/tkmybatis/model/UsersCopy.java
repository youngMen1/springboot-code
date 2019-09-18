package com.seal.tkmybatis.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: users_copy
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_copy")
public class UsersCopy {
    /**
     * id
     *
     * Table:     users_copy
     * Column:    id
     * Nullable:  false
     */
    @Id
    private Integer id;

    /**
     * 关联user_auth表
     *
     * Table:     users_copy
     * Column:    uid
     * Nullable:  false
     */
    @Id
    private String uid;

    /**
     * 安科id
     *
     * Table:     users_copy
     * Column:    anke_id
     * Nullable:  true
     */
    @Column(name = "anke_id")
    private String ankeId;

    /**
     * 手机号
     *
     * Table:     users_copy
     * Column:    phone
     * Nullable:  true
     */
    private String phone;

    /**
     * 举荐人
     *
     * Table:     users_copy
     * Column:    parent_id
     * Nullable:  true
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * openId
     *
     * Table:     users_copy
     * Column:    open_id
     * Nullable:  true
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 同一用户，对同一个微信开放平台下的不同应用，unionid是相同的。
     *
     * Table:     users_copy
     * Column:    union_id
     * Nullable:  true
     */
    @Column(name = "union_id")
    private String unionId;

    /**
     * 微信用户名
     *
     * Table:     users_copy
     * Column:    nick_name
     * Nullable:  true
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 性别 0：未知、1：男、2：女
     *
     * Table:     users_copy
     * Column:    gender
     * Nullable:  true
     */
    private String gender;

    /**
     * 城市
     *
     * Table:     users_copy
     * Column:    city
     * Nullable:  true
     */
    private String city;

    /**
     * 省份
     *
     * Table:     users_copy
     * Column:    province
     * Nullable:  true
     */
    private String province;

    /**
     * 国家
     *
     * Table:     users_copy
     * Column:    country
     * Nullable:  true
     */
    private String country;

    /**
     * 头像地址
     *
     * Table:     users_copy
     * Column:    avatar_url
     * Nullable:  true
     */
    @Column(name = "avatar_url")
    private String avatarUrl;

    /**
     * 账户来源
     *
     * Table:     users_copy
     * Column:    source
     * Nullable:  true
     */
    private String source;

    /**
     * 创建时间
     *
     * Table:     users_copy
     * Column:    create_datetime
     * Nullable:  true
     */
    @Column(name = "create_datetime")
    private Date createDatetime;

    /**
     * '状态 1:可用 0:不可用'
     *
     * Table:     users_copy
     * Column:    status
     * Nullable:  true
     */
    private String status;

    /**
     * 微信登录是否绑定(默认为0)
     *
     * Table:     users_copy
     * Column:    weixin_binding
     * Nullable:  true
     */
    @Column(name = "weixin_binding")
    private String weixinBinding;

    /**
     * 员工号登录是否绑定(0:未绑定 1:绑定)
     *
     * Table:     users_copy
     * Column:    staff_binding
     * Nullable:  true
     */
    @Column(name = "staff_binding")
    private String staffBinding;

    /**
     * 手机登录是否绑定(0:未绑定 1:已绑定)
     *
     * Table:     users_copy
     * Column:    phone_binding
     * Nullable:  true
     */
    @Column(name = "phone_binding")
    private String phoneBinding;
}