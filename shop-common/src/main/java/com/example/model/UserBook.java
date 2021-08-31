package com.example.model;

public enum UserBook {
    XIAO_MING(1, "小明"),
    XIAO_LI(1, "小李"),
    ZHANG_SAN(1, "张三");

    private Integer uid;
    private String userName;

    UserBook(int uid, String userName) {
        this.uid = uid;
        this.userName = userName;
    }

    public Integer getUid() {
        return uid;
    }

    public String getUserName() {
        return userName;
    }
}
