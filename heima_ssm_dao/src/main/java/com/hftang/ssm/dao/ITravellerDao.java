package com.hftang.ssm.dao;

import com.hftang.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-12 15:29
 * @desc
 */
public interface ITravellerDao {

    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{ordersId})")
    public List<Traveller> findByOrdersId(String ordersId);
}
