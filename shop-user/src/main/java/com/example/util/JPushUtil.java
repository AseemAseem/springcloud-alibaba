package com.example.util;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class JPushUtil {

    /**
     * 设置好账号的app_key和masterSecret是必须的
     * 用自己创建以用后的APP_KEY和MASTER_SECRET
     * 替换即可，后期可写到yml配置文件中
     */
    private static String APP_KEY = "dccda52cf9b3e574ee5beb13";
    private static String MASTER_SECRET = "1e69202b4db58124b00ad42c";

    // 极光推送>>Android
    //Map<String, String> parm是我自己传过来的参数,可以自定义参数

    public static void jpushAndroid(Map<String, String> parm) throws APIConnectionException, APIRequestException {

        // 创建JPushClient(极光推送的实例)
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        // 推送的关键,构造一个payload
        String alias = parm.get("alias");
        String registrationId = parm.get("registrationId");
        PushPayload payload = PushPayload.newBuilder()
                // 指定android平台的用户
                .setPlatform(Platform.android())
                // 你项目中的所有用户
                .setAudience(Audience.all())

                .setAudience(Audience.alias(alias)) // 设置别名发送,单发，点对点方式
                .setAudience(Audience.tag("tag")) // 设置按标签发送，相当于群发
                .setAudience(Audience.registrationId(registrationId)) // registrationId指定用户
                // 发送内容
                .setNotification(Notification.android(parm.get("msg"), parm.get("title"), parm))
                // apnProduction指定开发环境 true为生产模式 false 为测试模式 (android不区分模式,ios区分模式) 不用设置也没关系
                // TimeToLive 两个小时的缓存时间
//                .setOptions(Options.newBuilder().setApnsProduction(true).setTimeToLive(7200).build())
                // 自定义信息
                .setMessage(Message.content(parm.get("msg")))
                .setCid(parm.get("cid"))
                .build();


            String str = "{\n" +
                    "  \"platform\": [\"android\", \"ios\",\"quickapp\"],\n" +
                    "  \"audience\": {\n" +
                    "    \"tag\": [\n" +
                    "      \"Aseem\",\n" +
                    "      \"北京\"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"message\": {\n" +
                    "    \"msg_content\": \"应用内消息。或者称作：自定义消息，透传消息。\",\n" +
                    "    \"content_type\": \"text\",\n" +
                    "    \"title\": \"msg\",\n" +
                    "    \"extras\": {\n" +
                    "      \"key\": \"value\"\n" +
                    "    }\n" +
                    "  },\n" +
                    "  \"options\": {\n" +
                    "    \"time_to_live\": 60,\n" +
                    "    \"apns_production\": false,\n" +
                    "    \"apns_collapse_id\":\"jiguang_test_201706011100\"\n" +
                    "  }\n" +
                    "}";

            PushResult pu = jpushClient.sendPush(str);

            System.out.println(pu.toString());

    }

    // 极光推送>>ios

    // Map<String, String> parm是我自己传过来的参数,可以自定义参数

    public static void jpushIOS(Map<String, String> parm) {
        // 创建JPushClient
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        PushPayload payload = PushPayload.newBuilder()
                // ios平台的用户
                .setPlatform(Platform.ios())
                // 所有用户
                .setAudience(Audience.all())
                // registrationId指定用户
                //.setAudience(Audience.registrationId(parm.get("id")))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(parm.get("msg"))
                                .setBadge(+1)
                                // 这里是设置提示音
                                .setSound("happy")
                                .addExtras(parm)
                                .build())
                        .build())
                .setOptions(Options.newBuilder().setApnsProduction(false).build())
                // 自定义信息
                .setMessage(Message.newBuilder().setMsgContent(parm.get("msg")).addExtras(parm).build())
                .build();
        try {
            PushResult pu = jpushClient.sendPush(payload);
            log.info(String.valueOf(pu));
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }


    // 极光推送>>All所有平台

    public static void jpushAll(Map<String, String> param) {

        // 创建JPushClient
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        // 创建option
        PushPayload payload = PushPayload.newBuilder()
                // 所有平台的用户
                .setPlatform(Platform.all())
                // registrationId指定用户
                .setAudience(Audience.registrationId(param.get("id")))

                .setNotification(Notification.newBuilder()
                        // 发送ios
                        .addPlatformNotification(IosNotification.newBuilder()
                                // 消息体
                                .setAlert(param.get("msg"))
                                .setBadge(+1)
                                // ios提示音
                                .setSound("happy")
                                // 附加参数
                                .addExtras(param)
                                .build())
                        // 发送android
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                // 附加参数
                                .addExtras(param)
                                // 消息体
                                .setAlert(param.get("msg"))
                                .build())
                        .build())
                // 指定开发环境 true为生产模式 false 为测试模式 (android不区分模式,ios区分模式)
                .setOptions(Options.newBuilder().setApnsProduction(true).build())
                // 自定义信息
                .setMessage(Message.newBuilder().setMsgContent(param.get("msg")).addExtras(param).build())
                .build();
        try {
            PushResult pu = jpushClient.sendPush(payload);
            log.info(String.valueOf(pu));
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }
}
