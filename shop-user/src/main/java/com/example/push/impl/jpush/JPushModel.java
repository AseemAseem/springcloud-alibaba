package com.example.push.impl.jpush;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import com.example.push.MsgModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class JPushModel {
    // 消息id，非必须，防重复发送
    private String cid;

    // 推送平台，默认全部
    private String[] platform;

    // "推送范围
    private Audience audience;

    // 消息内容
    private Notification notification;

    // 应用内消息。或者称作：自定义消息，透传消息
    private Message message;

    // 可选参数
    private Options options;

    private JPushModel() {
    }

    public JPushModel(MsgModel msgModel) {
        this.cid = msgModel.getCid();
        this.platform = parsePlatformArr(msgModel.getPlatforms());
        this.audience = parseAudience(msgModel.getAudience());
        this.notification = parseNotification(msgModel.getNotification(), msgModel.getPlatforms());
        this.message = parseMessage(msgModel.getMessage());
        this.options = parseOptions(msgModel.getOptions());
    }

    private Options parseOptions(MsgModel.Options options) {
        if (options == null) {
            return new Options();
        }
        Options result = new Options();
        result.setTime_to_live(options.getTime_to_live());
        return result;
    }

    private Message parseMessage(MsgModel.Message message) {
        if (message == null) {
            return null;
        }
        Message result = new Message();
        result.setTitle(message.getTitle());
        result.setMsg_content(message.getContent());
        result.setContent_type(message.getContent_type());
        result.setExtras(message.getExtras());
        return result;
    }

    private Notification parseNotification(MsgModel.Notification notification, List<MsgModel.Platform> platforms) {
        Notification result = new JPushModel.Notification();
        boolean isEmpty = false;
        if (CollectionUtil.isEmpty(platforms)) {
            isEmpty = true;
        }
        if (isEmpty || platforms.contains(MsgModel.Platform.android)) {
            Android android = new Android();
            android.setTitle(notification.getTitle());
            android.setAlert(notification.getContent());
            android.setExtras(notification.getExtras());
//            android.setLarge_icon();
            result.setAndroid(android);
        }
        if (isEmpty || platforms.contains(MsgModel.Platform.ios)) {
            Ios ios = new Ios();
            ios.setAlert(notification.getContent());
            ios.setExtras(notification.getExtras());
            result.setIos(ios);
        }
        if (isEmpty || platforms.contains(MsgModel.Platform.quickapp)) {
            QuickApp quickApp = new QuickApp();
            quickApp.setTitle(notification.getTitle());
            quickApp.setAlert(notification.getContent());
//            quickApp.setPage();
            result.setQuickapp(quickApp);
        }
        return result;
    }

    private Audience parseAudience(MsgModel.Audience audience) {
        JPushModel.Audience temp = new JPushModel.Audience();
        if(ArrayUtil.isEmpty(audience.getTag())){
            temp.setTag(null);
        }
        if(CollectionUtil.isEmpty(new ArrayList<>())){
            temp.setTag(null);
        }

        temp.setTag(ArrayUtil.isEmpty(audience.getTag()) ? null : audience.getTag());
        temp.setTag_and(ArrayUtil.isEmpty(audience.getTag_and()) ? null : audience.getTag_and());
        temp.setTag_not(ArrayUtil.isEmpty(audience.getTag_not()) ? null : audience.getTag_not());
        temp.setRegistration_id(ArrayUtil.isEmpty(audience.getRegistration_id()) ? null : audience.getRegistration_id());
        temp.setAlias(ArrayUtil.isEmpty(audience.getAlias()) ? null : audience.getAlias());
        return temp;
    }

    private static String[] parsePlatformArr(List<MsgModel.Platform> platforms) {
        // 极光推送中推送平台只有这几种。严格区分大小写。默认推送全部
        String[] defaultValue = {"android", "ios", "quickapp"};
        if (CollectionUtil.isEmpty(platforms)) {
            return defaultValue;
        }

        String[] result = new String[platforms.size()];
        for (int i = 0; i < platforms.size(); i++) {
            MsgModel.Platform temp = platforms.get(i);
            result[i] = temp.name();
        }

        return result;
    }

    @Data
    private class Audience {
        // 包含的标签
        private String[] tag;

        // 标签并集
        private String[] tag_and;

        // 需要排除的标签，非必须
        private String[] tag_not;

        // 设备标识id，非必须
        private String[] registration_id;

        // 设备别名，非必须
        private String[] alias;
    }

    @Data
    private class Notification {
        private Android android;
        private Ios ios;
        private QuickApp quickapp;
    }


    @Data
    private class Android {
        // 内容
        private String alert;
        private String title;
        private Integer builder_id = 1;
        private String large_icon;
        private Map<String, String> extras;
    }

    @Data
    private class Ios {
        private String alert;
        private String sound;
        private String badge = "+1";
        // ios通知分组id，可选
//                private String   thread-id;
        private Map<String, String> extras;
    }

    @Data
    private class QuickApp {
        private String alert;
        private String title;
        // 必填，快应用跳转页面
        private String page = "/page1";
    }

    // 应用内消息。或者称作：自定义消息，透传消息
    @Data
    private class Message {
        private String msg_content;
        private String content_type = "text";
        private String title;
        private Map<String, String> extras;
    }

    // 可选参数
    @Data
    private class Options {
        // 用户离线时，消息保留时长，默认10天
        private Integer time_to_live = 10 * 86400;
        // iOS是否生产环境，仅对ios生效
        private boolean apns_production = true;
        //  iOS通知id，相同则覆盖
        private String apns_collapse_id;
    }
}
