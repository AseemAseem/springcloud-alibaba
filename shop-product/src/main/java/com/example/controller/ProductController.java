package com.example.controller;

import com.example.domain.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/product/{pid}")
    public Product product(@PathVariable("pid") Integer pid){
        Product product = productService.findByPid(pid);
        return product;
    }
}
