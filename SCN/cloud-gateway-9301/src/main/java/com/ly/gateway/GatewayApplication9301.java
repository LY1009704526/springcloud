package com.ly.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author LY
 * @Description TODO
 * @Date 2022年05月05日 18:55:00
 */

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication9301 {

    public static void main(String[] args) {

        SpringApplication.run(GatewayApplication9301.class,args);
    }

}
