package com.seal.security.controller;

import com.seal.security.constant.HttpResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户控制器
 *
 * @author fengzhiqiang
 * @date-time 2020/4/7-14:20
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @PreAuthorize("hasAuthority('sys:user:view')")
    @GetMapping(value = "/findAll")
    public HttpResult findAll() {
        return HttpResult.ok("the findAll service is called success.");
    }

    @PreAuthorize("hasAuthority('sys:user:edit')")
    @GetMapping(value = "/edit")
    public HttpResult edit() {
        return HttpResult.ok("the edit service is called success.");
    }

    @PreAuthorize("hasAuthority('sys:user:delete')")
    @GetMapping(value = "/delete")
    public HttpResult delete() {
        return HttpResult.ok("the delete service is called success.");
    }

}
