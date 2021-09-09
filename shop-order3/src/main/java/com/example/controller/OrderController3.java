package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController3 {

    @RequestMapping("/order/sentinel")
    public String order() {
        return "success";
    }
}

