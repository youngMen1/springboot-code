package com.seal.security.constant;


import lombok.Data;

/**
 * 登录接口封装对象
 *
 * @author fengzhiqiang
 * @date-time 2020/4/7-14:20
 **/
@Data
public class LoginBean {
    private String username;
    private String password;
}
