package com.seal.tkmybatis.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: message_configuration
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "message_configuration")
public class MessageConfiguration {
    /**
     * id
     *
     * Table:     message_configuration
     * Column:    id
     * Nullable:  false
     */
    @Id
    private Integer id;

    /**
     * 消息模板id
     *
     * Table:     message_configuration
     * Column:    template_id
     * Nullable:  true
     */
    @Column(name = "template_id")
    private String templateId;

    /**
     * 消息模板类型
     *
     * Table:     message_configuration
     * Column:    template_type
     * Nullable:  true
     */
    @Column(name = "template_type")
    private String templateType;

    /**
     * 创建时间
     *
     * Table:     message_configuration
     * Column:    create_datetime
     * Nullable:  true
     */
    @Column(name = "create_datetime")
    private Date createDatetime;
}