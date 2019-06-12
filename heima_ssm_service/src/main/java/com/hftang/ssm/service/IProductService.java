package com.hftang.ssm.service;

import com.hftang.ssm.domain.Product;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-11 11:01
 * @desc
 */
public interface IProductService {

    public List<Product> findAll() throws Exception;

    void save(Product product);
}
