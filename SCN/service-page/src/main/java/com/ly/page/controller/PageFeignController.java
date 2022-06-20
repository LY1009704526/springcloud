package com.ly.page.controller;


import com.ly.common.pojo.Product;
import com.ly.page.feign.ProductFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/page/feign")
public class PageFeignController {

    @Autowired
    private ProductFeign productFeign;

    @GetMapping("/getProductById/{id}")
    public Product getProductById(@PathVariable Integer id) {

        return productFeign.findById(id);
    }

    @GetMapping("/getProductPort")
    public String getProductPort() {

        return productFeign.getPort();
    }

    @GetMapping("/getProductPort2")
    public String getProductPort2() {

        return productFeign.getPort();
    }

    @GetMapping("/getProductPort3")
    public String getProductPort3() {

        return productFeign.getPort();
    }

}
