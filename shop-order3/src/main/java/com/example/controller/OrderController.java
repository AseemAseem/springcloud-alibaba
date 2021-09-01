package com.example.controller;

import com.example.domain.Order;
import com.example.domain.Product;
import com.example.model.UserBook;
import com.example.remote.ProductServiceApi;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private ProductServiceApi productServiceApi;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        Order order = new Order();
        order.setUid(UserBook.XIAO_MING.getUid());
        order.setUsername(UserBook.XIAO_MING.getUserName());

        Product product = productServiceApi.findByPid(pid);
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);

        orderService.createOrder(order);

        return order;
    }
}

