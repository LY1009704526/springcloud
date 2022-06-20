package com.ly.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Author LY
 * @Description TODO
 * @Date 2022年05月05日 21:22:00
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer // 开启配置服务器功能
public class ConfigServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(ConfigServerApplication.class,args);
    }

}
