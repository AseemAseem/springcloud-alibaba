package com.example.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "shop_order")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oid;

    // 用户
    private Integer uid;
    private String username;

    // 商品
    private Integer pid;
    private String pname;
    private Double pprice;

    // 数量
    private Integer number;
}