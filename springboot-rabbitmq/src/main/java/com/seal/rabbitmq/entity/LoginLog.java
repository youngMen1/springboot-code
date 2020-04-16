package com.seal.rabbitmq.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginLog implements Serializable {

    private static final long serialVersionUID = 9035584359424322830L;

    private Integer id;
    private Integer userId;
    private Integer type;
    private String description;
    private Date createTime;
    private Date updateTime;
    /**
     * 消息id
     */
    private String msgId;

}
