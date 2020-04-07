package com.seal.security.model;

import lombok.Data;

/**
 * 用户模型
 *
 * @author fengzhiqiang
 * @date-time 2020/4/7-14:20
 **/
@Data
public class User {

    private Long id;

    private String username;

    private String password;

}