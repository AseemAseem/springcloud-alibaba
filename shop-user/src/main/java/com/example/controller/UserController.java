package com.example.controller;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import com.example.service.UserService;
import com.example.util.jpush.JPushUtil;
import com.example.util.jpush.MsgModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户管理")
@RestController
public class UserController {

    @ApiOperation("推送消息")
    @PostMapping("/user/push")
    public String jpush(@RequestBody MsgModel msgModel) {
        try {
            JPushUtil.jpush(msgModel);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "success";
    }
}
