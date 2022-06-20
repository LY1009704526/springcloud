package com.ly.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author LY
 * @Description TODO
 * @Date 2022年04月27日 19:44:00
 */

@SpringBootApplication
@EnableEurekaServer // 表示当前项目为Eureka server
public class EurekaApplication9201 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication9201.class,args);
    }

}
