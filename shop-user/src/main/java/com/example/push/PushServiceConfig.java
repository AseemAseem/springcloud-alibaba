package com.example.push;


import cn.hutool.core.util.StrUtil;
import com.example.push.impl.jpush.JPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PushServiceConfig {
    @Value("${push.active}")
    private String active;

    @Autowired
    private JPushService jPushService;

    @Bean
    public PushService pushService() {
        if (StrUtil.isEmpty(active)) {
            return jPushService;
        }
        if (active.equalsIgnoreCase("jpush")) {
            return jPushService;
        } else {
            return jPushService;
        }
    }
}
