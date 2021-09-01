package com.example.util.jpush;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import com.alibaba.nacos.client.utils.JSONUtils;
import com.google.gson.JsonObject;
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

    public static void jpush(MsgModel msgModel) throws APIConnectionException, APIRequestException {
        // 创建JPushClient(极光推送的实例)
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
//        String str = "{\n" +
//                "  \"platform\": [\""+ MsgModel.Patform.android.getType()+"\"],\n" +
//                "  \"audience\": {\n" +
//                "    \"tag\": [\n" +
//                "      \"Aseem\",\n" +
//                "      \"北京\"\n" +
//                "    ]\n" +
//                "  },\n" +
//                "  \"message\": {\n" +
//                "    \"msg_content\": \"应用内消息。或者称作：自定义消息，透传消息。\",\n" +
//                "    \"content_type\": \"text\",\n" +
//                "    \"title\": \"msg\",\n" +
//                "    \"extras\": {\n" +
//                "      \"key\": \"value\"\n" +
//                "    }\n" +
//                "  },\n" +
//                "  \"options\": {\n" +
//                "    \"time_to_live\": 60,\n" +
//                "    \"apns_production\": false,\n" +
//                "    \"apns_collapse_id\":\"jiguang_test_201706011100\"\n" +
//                "  }\n" +
//                "}";

        JSON parse = JSONUtil.parse(msgModel);
        String str = parse.toJSONString(0);
        System.out.println();
        System.out.println(str);
        System.out.println();
        PushResult pu = jpushClient.sendPush(str);

        System.out.println(pu.toString());

    }
}
