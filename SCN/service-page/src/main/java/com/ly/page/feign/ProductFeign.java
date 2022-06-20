package com.ly.page.feign;

import com.ly.common.pojo.Product;
import com.ly.page.feign.impl.ProductFeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author LY
 * @Description TODO
 * @Date 2022年04月30日 17:43:00
 */
@FeignClient(name = "service-product",fallback = ProductFeignFallBack.class)
public interface ProductFeign {

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    @GetMapping("/product/findById/{id}") // url:需要全路径
    public Product findById(@PathVariable Integer id);

    /**
     * 获取端口号
     * @return
     */
    @GetMapping("/service/getPort")
    public String getPort();

}
