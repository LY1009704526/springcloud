package com.ly.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LY
 * @Description TODO
 * @Date 2022年04月28日 18:31:00
 */

@RestController
@RequestMapping("/service")
public class ServiceInfoController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/getPort")
    public String getPort() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return port;
    }

}
