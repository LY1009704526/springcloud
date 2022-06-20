package com.ly.page.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LY
 * @Description TODO
 * @Date 2022年05月05日 22:12:00
 */

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigClientController {

    @Value("${mysql.user}")
    private String user;

    @Value("${person.name}")
    private String name;

    @GetMapping("/getConfigInfo")
    public String getConfigInfo() {

        return user + "," + name;
    }


}
