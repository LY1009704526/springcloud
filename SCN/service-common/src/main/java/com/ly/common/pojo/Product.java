package com.ly.common.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author LY
 * @Description TODO
 * @Date 2022年04月27日 18:14:00
 */

@Data
@Table(name = "product")
public class Product {

    @Id
    private Integer id;
    private String name;
    private double price;
    private String flag;
    private String goodsDesc;
    private String images;
    private Integer goodsStock;
    private String goodsType;

}

