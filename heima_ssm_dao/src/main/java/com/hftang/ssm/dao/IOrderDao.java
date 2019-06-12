package com.hftang.ssm.dao;

import com.hftang.ssm.domain.Member;
import com.hftang.ssm.domain.Orders;
import com.hftang.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-12 9:33
 * @desc
 */
public interface IOrderDao {

    //1 查询所有订单
    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.hftang.ssm.dao.IProductDao.findProductById")),
    })
    public List<Orders> findAll() throws Exception;

    //2根据orderid 来查询 订单详情
    @Select("select * from orders where id = #{orderId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.hftang.ssm.dao.IProductDao.findProductById")),
            @Result(property = "member", column = "memberId", javaType = Member.class, one = @One(select = "com.hftang.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers", column = "id", javaType = java.util.List.class, many = @Many(select = "com.hftang.ssm.dao.ITravellerDao.findByOrdersId")),
    })
    Orders findById(String orderId)throws Exception;
}
