package com.example.push;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MsgModel {
    @ApiModelProperty("消息id，防重复发送")
    private String cid;

    @ApiModelProperty("推送平台，默认全部")
    private List<Platform> platforms;

    @ApiModelProperty(value = "推送范围")
    private Audience audience;

    @ApiModelProperty(value = "消息内容", required = true)
    private Notification notification;

    @ApiModelProperty("应用内消息。或者称作：自定义消息，透传消息")
    private Message message;

    @ApiModelProperty("可选参数")
    private Options options;

    @Data
    public class Audience {
        @ApiModelProperty("包含的标签")
        private String[] tag;

        @ApiModelProperty("标签并集")
        private String[] tag_and;

        @ApiModelProperty("需要排除的标签，非必须")
        private String[] tag_not;

        @ApiModelProperty("设备标识id，非必须")
        private String[] registration_id;

        @ApiModelProperty("设备别名，非必须")
        private String[] alias;
    }

    @Data
    public class Notification {
        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty("内容")
        private String content;

        // 扩展字段
        private Map<String, String> extras;
    }

    // 应用内消息。或者称作：自定义消息，透传消息
    @Data
    public class Message {
        private String title;
        private String content;
        // 消息内容类型
        private String content_type = "text";
        // 扩展字段
        private Map<String, String> extras;
    }

    // 可选参数
    @Data
    public class Options {
        // 用户离线时，消息保留时长，默认10天
        private Integer time_to_live = 10 * 86400;
        // iOS是否生产环境，仅对ios生效
        private boolean apns_production = true;
        //  iOS通知id，相同则覆盖
        private String apns_collapse_id;
    }

    // 推送平台，默认全部
    public enum Platform {
        android,
        ios,
        quickapp;
    }
}