package com.hftang.ssm.dao;

import com.hftang.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-11 10:57
 * @desc
 */

public interface IProductDao {

    //根据id查询产品
    @Select("select * from product where id = #{id}")
    public Product findProductById(String id) throws Exception;

    //查询所有的商品信息
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

}
