package com.example.service;

import com.example.domain.Product;
import org.springframework.stereotype.Service;

public interface ProductService {
    Product findByPid(Integer pid);
}