package com.example.push.impl.jpush;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import com.example.push.MsgModel;
import com.example.push.PushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JPushService implements PushService {
    @Value("${push.jpush.appKey}")
    private String appKey;

    @Value("${push.jpush.masterSecret}")
    private String masterSecret;

    public void push(MsgModel msgModel) {
        JPushModel jPushModel = new JPushModel(msgModel);
        JSONObject jsonObject = JSONUtil.parseObj(jPushModel);
        String str = jsonObject.toJSONString(0);

        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        PushResult pu = null;
        try {
            pu = jpushClient.sendPush(str);
            log.info("jpush push result: " + pu);
        } catch (Exception e) {
            // TODO: 抛出业务异常
            e.printStackTrace();
        }
    }
}
