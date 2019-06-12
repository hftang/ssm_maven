package com.hftang.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.hftang.ssm.dao.IOrderDao;
import com.hftang.ssm.domain.Orders;
import com.hftang.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-12 9:17
 * @desc
 */
@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrderDao iOrderDao;

    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        //pageNum页码值  pagesize每一页显示的条数
        PageHelper.startPage(page, size);
        return iOrderDao.findAll();
    }

    @Override
    public Orders findById(String orderId) throws Exception {
        return iOrderDao.findById(orderId);
    }
}
