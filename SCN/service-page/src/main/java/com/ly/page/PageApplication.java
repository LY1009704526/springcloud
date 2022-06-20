package com.ly.page;

import okhttp3.ConnectionPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;


/**
 * @Author LY
 * @Description TODO
 * @Date 2022年04月27日 18:43:00
 */

@SpringBootApplication
// @EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker // 启用熔断服务
@EnableFeignClients // 开启Feign客户端功能
public class PageApplication {

    public static void main(String[] args) {
        SpringApplication.run(PageApplication.class,args);
    }

    /**
     * 向容器中注入一个RestTemplate，封装了HttpClient
     * @return
     */
    @Bean
    @LoadBalanced // Ribbon自动请求负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Spring IOC容器初始化时构建okHttpClient对象
     * @return
     */
    @Bean
    public okhttp3.OkHttpClient okHttpClient() {
        return new okhttp3.OkHttpClient().newBuilder()
                // 读取超时时间
                .readTimeout(10, TimeUnit.SECONDS)
                // 连接超时时间
                .connectTimeout(10,TimeUnit.SECONDS)
                // 写超时时间
                .writeTimeout(10,TimeUnit.SECONDS)
                // 设置连接池
                .connectionPool(new ConnectionPool())
                .build();
    }

}
