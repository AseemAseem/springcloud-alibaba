package com.example.controller;

import com.example.push.MsgModel;
import com.example.push.PushService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户管理")
@RestController
public class UserController {
    @Autowired
    private PushService pushService;

    @ApiOperation("推送消息")
    @PostMapping("/user/push")
    public String jpush(@RequestBody MsgModel msgModel) {
        try {
            pushService.push(msgModel);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "success";
    }
}