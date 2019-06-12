package com.hftang.ssm.service.impl;

import com.hftang.ssm.dao.IProductDao;
import com.hftang.ssm.domain.Product;
import com.hftang.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-11 11:03
 * @desc
 */
@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao iProductDao;


    @Override
    public List<Product> findAll() throws Exception {

        return iProductDao.findAll();
    }

    @Override
    public void save(Product product) {
        iProductDao.save(product);
    }
}
