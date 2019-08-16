package com.seal.log.controller;

import com.seal.log.annotation.SystemControllerLog;
import com.seal.log.model.ExecutionResult;
import com.seal.log.model.User;
import com.seal.log.service.UserService;
import com.seal.log.util.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/userlist")
    @SystemControllerLog(descrption = "查询用户信息", actionType = "4")
    public ExecutionResult getUserList(String id) throws Exception {

        ExecutionResult result = new ExecutionResult();
        try {
            List<User> users = userService.findAll();
            result.setTotal(users.size());
            result.setResultCode(ReturnCode.RES_SUCCESS);
            result.setFlag(true);
            result.setData(users);
            result.setMsg("查询成功！");
            //异常处理
            int aa = 5 / 0;
        } catch (Exception e) {
            result.setFlag(true);
            result.setData(null);
            result.setResultCode(ReturnCode.RES_FAILED);
            result.setMsg("查询失败！");
            throw e;
        }
        return result;
    }

}
