package com.hftang.ssm.service;

import com.hftang.ssm.domain.Orders;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-12 9:17
 * @desc
 */
public interface IOrdersService {

    public List<Orders> findAll(int page,int size) throws Exception;

    Orders findById(String orderId) throws Exception;
}
