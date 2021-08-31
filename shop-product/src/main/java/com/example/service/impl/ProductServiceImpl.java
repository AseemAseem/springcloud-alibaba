package com.example.service.impl;

import com.example.dao.ProductDao;
import com.example.domain.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public Product findByPid(Integer pid) {
        Optional<Product> byId = productDao.findById(pid);
        return byId.isPresent() ? byId.get() : null;
    }
}
