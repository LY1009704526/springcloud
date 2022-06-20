package com.ly.page.feign.impl;

import com.ly.common.pojo.Product;
import com.ly.page.feign.ProductFeign;
import org.springframework.stereotype.Component;

/**
 * @Author LY
 * @Description 熔断器触发之后的回调逻辑
 * @Date 2022年04月30日 18:23:00
 */

@Component
public class ProductFeignFallBack implements ProductFeign {

    @Override
    public Product findById(Integer id) {
        return null;
    }

    @Override
    public String getPort() {
        return "Feign:-1";
    }

}
