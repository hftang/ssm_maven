package com.hftang.ssm.controller;

import com.hftang.ssm.domain.Permission;
import com.hftang.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-17 10:57
 * @desc
 */
@RequestMapping("/permission")
@Controller
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mv.setViewName("permission-list");
        mv.addObject("permissionList", permissionList);
        return mv;
    }

    //保存

    @RequestMapping("/save.do")
    public String save(Permission permission) {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }
}
