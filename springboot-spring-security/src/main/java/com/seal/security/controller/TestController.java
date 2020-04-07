package com.seal.security.controller;

import com.seal.security.constant.HttpResult;
import com.seal.security.constant.LoginBean;
import com.seal.security.security.JwtAuthenticatioToken;
import com.seal.security.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/7-14:20
 **/
@RestController
@Api(value = "TestController", tags = "测试接口")
public class TestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 登录接口
     */
    @PostMapping(value = "/login")
    @ApiOperation(value = "登录接口", notes = "登录接口")
    public HttpResult login(@Validated @RequestBody LoginBean loginBean, HttpServletRequest request) {

        String username = loginBean.getUsername();
        String password = loginBean.getPassword();

        // 系统登录认证
        JwtAuthenticatioToken token = SecurityUtils.login(request, username, password, authenticationManager);

        System.out.println(token);

        return HttpResult.ok(token);
    }

}
