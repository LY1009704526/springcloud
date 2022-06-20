package com.ly.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author LY
 * @Description TODO
 * @Date 2022年04月27日 18:15:00
 */

@SpringBootApplication
@MapperScan("com.ly.product.mapper")
// @EnableEurekaClient // 将当前项目作为Eureka Client注册到Eureka Server，只能在Eureka环境中使用
@EnableDiscoveryClient // 也是将当前项目表示为注册中心的客户端，向注册中心进行注册，可以在所以的服务注册中心环境下使用
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class,args);
    }

}
