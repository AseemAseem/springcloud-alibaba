package com.example.controller;

import com.example.util.JPushUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @RequestMapping("/user/push")
    public String jpush(
                        @RequestParam(value = "alias", required = false) String cid,
                        @RequestParam(value = "alias", required = false) String alias,
                        @RequestParam(value = "id", required = false) String registrationId,
                        @RequestParam(value = "tag", required = false) String tag) {
//        String alias = "Aseem";
//        String tag = "Aseem";
//        String registrationId = "100d8559099d99a524d";

        Map<String, String> parm = new HashMap<String, String>();
        // 推送目标
        parm.put("alias", alias);
        parm.put("registrationId", registrationId);// 发送给某一台
        parm.put("tag", tag);// 发送给某一台

        // 标题和内容
        parm.put("title", "川普邀请你打吃鸡(by springboot)");

        StringBuffer sb = new StringBuffer();
        sb.append("alias").append(":").append(alias).append("    ");
        sb.append("tag").append(":").append(tag).append("    ");
        sb.append("registrationId").append(":").append(registrationId).append("    ");
        parm.put("msg", sb.toString());

        try {
            JPushUtil.jpushAndroid(parm);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "success";
    }
}
