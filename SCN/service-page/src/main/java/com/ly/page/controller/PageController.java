package com.ly.page.controller;


import com.ly.common.pojo.Product;
import com.ly.page.feign.ProductFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/page")
public class PageController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ProductFeign productFeign;

    @GetMapping("/getProductById/{id}")
    public Product getProductById(@PathVariable Integer id) {

        // 获取service-product在服务注册中心注册的服务列表
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance instance = instances.get(0);

        // 获取服务元数据
        Map<String, String> metadata = instance.getMetadata();

        // 获取商品微服务的主机地址
        String host = instance.getHost();
        // 获取商品微服务的端口号
        int port = instance.getPort();
        // String url = "http://" + host + ":" + port +"product/findById/";
        String url = "http://service-product/product/findById/";
        // 发送Http请求给商品微服务，将商品id传过去，获取对应product对象
        // String url = "http://127.0.0.1:9000/product/findById/";
        Product product = restTemplate.getForObject(url + id, Product.class);
        return product;
    }

    @GetMapping("/getProductPort")
    public String getProductPort() {

        String url = "http://service-product/service/getPort";

        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    /**
     * 模拟服务超时，进行熔断处理
     * 针对熔断处理，Hystrix默认维护一个线程池，默认大小为10
     * @return
     */
    @HystrixCommand(
            // 默认所有的请求共同维护一个线程池，实际开发中，每个方法维护一个线程池
            // 只要在@HystrixCommand中定义了threadPoolKey，就意味着开启了舱壁模式，该方法就会自己维护一个线程池
            threadPoolKey = "getProductPort2",
            // 每一个属性对应的都是一个HystrixProperty
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"), // 并发线程数
                    @HystrixProperty(name = "maxQueueSize", value = "20") // 默认线程队列是-1，默认不开启
            },
            // 超时时间的设置
            commandProperties = {
                    // 设置请求的超时时间，一旦请求超过此时间都按照超时处理，默认超时时间为1s
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    @GetMapping("/getProductPort2")
    public String getProductPort2() {

        String url = "http://service-product/service/getPort";

        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    /**
     * 服务降级，实在服务熔断之后的兜底操作
     * @return
     */
    @HystrixCommand(
            threadPoolKey = "getProductPort3",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "10"), // 并发线程数
                    @HystrixProperty(name = "maxQueueSize", value = "20") // 默认线程队列是-1，默认不开启
            },
            // 超时时间的设置
            commandProperties = {
                    // 设置请求的超时时间，一旦请求超过此时间都按照超时处理，默认超时时间为1s
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    // 统计窗口时间的设置，默认为10s
                    @HystrixProperty(name =
                            "metrics.rollingStats.timeInMilliseconds",value = "8000"),
                    // 统计窗口内的最小请求数
                    @HystrixProperty(name =
                            "circuitBreaker.requestVolumeThreshold",value = "2"),
                    // 统计窗口内错误请求阈值的设置 50%
                    @HystrixProperty(name =
                            "circuitBreaker.errorThresholdPercentage",value = "50"),
                    // 走修复的活动窗口时间的设置，默认为5s
                    @HystrixProperty(name =
                            "circuitBreaker.sleepWindowInMilliseconds",value = "10000")
            },
            // 设置回退方法
            fallbackMethod = "getProductPort3FallBack"
    )
    @GetMapping("/getProductPort3")
    public String getProductPort3() {

        String url = "http://service-product/service/getPort";

        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    /**
     * 定义回退方法，党情秋发出熔断后执行，补救措施
     * 注意：
     *  1.方法形参和原方法保持一致
     *  2.方法的返回值与原方法保持一致
     * @return
     */
    public String getProductPort3FallBack() {
        return "-1";
    }

}
