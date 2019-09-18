package com.seal.tkmybatis.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: user_type
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_type")
public class UserType {
    /**
     * Table:     user_type
     * Column:    id
     * Nullable:  false
     */
    @Id
    private String id;

    /**
     * Table:     user_type
     * Column:    user_id
     * Nullable:  false
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * Table:     user_type
     * Column:    user_type
     * Nullable:  false
     */
    @Column(name = "user_type")
    private Integer userType;
}