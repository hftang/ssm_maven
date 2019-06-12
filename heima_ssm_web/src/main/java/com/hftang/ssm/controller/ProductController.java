package com.hftang.ssm.controller;

import com.hftang.ssm.domain.Product;
import com.hftang.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-11 13:37
 * @desc
 */

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    //查询所有产品
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> list = iProductService.findAll();
        modelAndView.addObject("productList", list);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }

    //保存产品
    @RequestMapping("/save.do")
    public String saveProduct(Product product) throws Exception {

        iProductService.save(product);

        return "redirect:findAll.do";

    }
}
