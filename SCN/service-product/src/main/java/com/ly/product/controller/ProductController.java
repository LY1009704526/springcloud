package com.ly.product.controller;

import com.ly.common.pojo.Product;
import com.ly.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LY
 * @Description TODO
 * @Date 2022年04月27日 18:26:00
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/findById/{id}")
    public Product findById(@PathVariable Integer id) {
        return productService.findById(id);
    }

}
