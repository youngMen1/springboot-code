package com.seal.tkmybatis.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: user_open_id
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_open_id")
public class UserOpenId {
    /**
     * Table:     user_open_id
     * Column:    id
     * Nullable:  false
     */
    @Id
    private Integer id;

    /**
     * Table:     user_open_id
     * Column:    uid
     * Nullable:  false
     */
    private String uid;

    /**
     * Table:     user_open_id
     * Column:    open_id
     * Nullable:  false
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * Table:     user_open_id
     * Column:    source
     * Nullable:  true
     */
    private String source;
}