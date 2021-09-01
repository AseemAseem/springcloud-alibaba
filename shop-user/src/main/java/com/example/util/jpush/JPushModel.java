//package com.example.util.jpush;
//
//import cn.hutool.core.collection.CollectionUtil;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class JPushModel {
//    @ApiModelProperty("消息id，非必须，防重复发送")
//    private String cid;
//
//    @ApiModelProperty("推送设备，默认全部")
//    private String[] patform = new String[]{"android", "ios", "quickapp"};
//
//    @ApiModelProperty(value = "推送范围")
//    private Audience audience;
//
//    @ApiModelProperty("消息内容")
//    private Notification notification;
//
//    @ApiModelProperty("应用内消息。或者称作：自定义消息，透传消息")
//    private Message message;
//
//    @ApiModelProperty("可选参数")
//    private Options options;
//
//    private JPushModel() {
//    }
//
//    public JPushModel(MsgModel msgModel) {
//        this.cid = msgModel.getCid();
//
//
//        List<MsgModel.Patform> patforms = msgModel.getPatform();
//        if (CollectionUtil.isNotEmpty(patforms)) {
//            List<String> tempList = new ArrayList<>();
//            for (MsgModel.Patform temp : patforms) {
//                tempList.add(temp.getType())
//            }
//        }
//
//
//        this.patform =;
//    }
//
//    @Data
//    private class Audience {
//        @ApiModelProperty("包含的标签")
//        private String[] tag;
//
//        @ApiModelProperty("标签并集")
//        private String[] tag_and;
//
//        @ApiModelProperty("需要排除的标签，非必须")
//        private String[] tag_not;
//
//        @ApiModelProperty("设备标识id，非必须")
//        private String[] registration_id;
//    }
//
//    @Data
//    private class Notification {
//        private Android android;
//        private Ios ios;
//        private QuickApp quickapp;
//
//        private class Android {
//            // 内容
//            private String alert;
//            private String title;
//            private Integer builder_id = 1;
//            private String large_icon;
//            private Map<String, String> extras;
//        }
//
//        private class Ios {
//            private String alert;
//            private String sound;
//            private String badge = "+1";
//
//            // ios通知分组id，可选
////                private String   thread-id;
//            private Map<String, String> extras;
//        }
//
//        private class QuickApp {
//            private String alert;
//            private String title;
//            // 必填，快应用跳转页面
//            private String page = "/page1";
//        }
//    }
//
//    // 应用内消息。或者称作：自定义消息，透传消息
//    @Data
//    private class Message {
//        private String msg_content;
//        private String content_type = "text";
//        private String title;
//        private Map<String, String> extras;
//    }
//
//    // 可选参数
//    @Data
//    private class Options {
//        // 用户离线时，消息保留时长，默认10天
//        private Integer time_to_live = 10 * 86400;
//        // iOS是否生产环境，仅对ios生效
//        private boolean apns_production = true;
//        //  iOS通知id，相同则覆盖
//        private String apns_collapse_id;
//    }
//
//    // 推送设备类型，默认全部
//    private class JPushPatform {
//        private String android = "android";
//        private String ios = "ios";
//        private String quickapp = "quickapp";
//
//        public String[] toPatformArr(List<MsgModel.Patform> patforms) {
//            if (CollectionUtil.isEmpty(patforms)) {
//                return new String[0];
//            }
//
//            String[] result = new String[patforms.size()];
//            for (int i = 0; i < patforms.size(); i++) {
//                MsgModel.Patform temp = patforms.get(i);
//                if (MsgModel.Patform.ANDROID == temp) {
//                    result[i] = android;
//
//                } else if (MsgModel.Patform.IOS == temp) {
//                    result[i] = ios;
//
//                } else if (MsgModel.Patform.QUICK_APP == temp) {
//                    result[i] = quickapp;
//                }
//            }
//
//            return result;
//        }
//    }
//}
