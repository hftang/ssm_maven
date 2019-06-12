package com.hftang.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.hftang.ssm.domain.Orders;
import com.hftang.ssm.service.IOrdersService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-12 9:15
 * @desc
 */

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService iOrdersService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") int page,
                                @RequestParam(name = "size", required = true, defaultValue = "2") int size) throws Exception {
        ModelAndView mv = new ModelAndView();

        List<Orders> list = iOrdersService.findAll(page, size);

        PageInfo pageInfo = new PageInfo(list);

        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-page-list");

//        mv.setViewName("orders-list");
//        mv.addObject("ordersList", list);

        return mv;
    }

    //查看订单详情 findById
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true, defaultValue = "1") String orderId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = iOrdersService.findById(orderId);
        mv.setViewName("orders-show");
        mv.addObject("orders", orders);

        return mv;

    }


}
