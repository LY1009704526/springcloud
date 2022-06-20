package com.ly.product.service;

import com.ly.common.pojo.Product;

/**
 * @Author LY
 * @Description TODO
 * @Date 2022年04月27日 18:23:00
 */
public interface ProductService {

    /**
     * 根据商品id查询商品信息
     * @param id
     * @return
     */
    Product findById(Integer id);

}
