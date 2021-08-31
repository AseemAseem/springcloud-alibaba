package com.example.service;

import com.example.domain.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    void createOrder(Order order);
}
