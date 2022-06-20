package com.ly.product.service.impl;

import com.ly.common.pojo.Product;
import com.ly.product.mapper.ProductMapper;
import com.ly.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author LY
 * @Description TODO
 * @Date 2022年04月27日 18:25:00
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product findById(Integer id) {
        return productMapper.selectById(id);
    }

}
