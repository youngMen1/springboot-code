package com.seal.swagger.controller;

import com.seal.swagger.config.R;
import com.seal.swagger.dto.TravellerInfoDto;
import com.seal.swagger.request.MenuRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/13 13:54
 * @description
 **/
@Slf4j
@RestController
@RequestMapping(value = "/api/information")
@Api(value = "TestController", tags = "测试接口")
public class TestController {

    @PostMapping("/addMenu")
    @ApiOperation(value = "添加公众号菜单栏", notes = "添加公众号菜单栏")
    public R<TravellerInfoDto> addMenu(@Validated @RequestBody MenuRequest menuRequest) {
        System.out.println(menuRequest);
        TravellerInfoDto travellerInfoDto = new TravellerInfoDto();
        travellerInfoDto.setFirstName("feng");
        travellerInfoDto.setLastName("zhiqiang");
        travellerInfoDto.setSpecialRequest("用车");
        return new R<>(travellerInfoDto);


    }
}
