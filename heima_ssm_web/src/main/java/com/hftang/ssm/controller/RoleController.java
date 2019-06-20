package com.hftang.ssm.controller;

import com.hftang.ssm.domain.Permission;
import com.hftang.ssm.domain.Role;
import com.hftang.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-17 9:43
 * @desc
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService iRoleService;

    //根据roleid查询role，并添加可以添加的权限

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String roleId) {

        ModelAndView mv = new ModelAndView();

        //根据roleid 查询出来 role
        Role role = iRoleService.findRoleById(roleId);

        List<Permission> permissionList = iRoleService.findOtherPermission(roleId);

        mv.setViewName("role-permission-add");

        mv.addObject("role", role);
        mv.addObject("permissionList", permissionList);

        return mv;


    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {

        ModelAndView mv = new ModelAndView();

        List<Role> roleList = iRoleService.findAll();
        mv.addObject("roleList", roleList);
        mv.setViewName("role-list");

        return mv;

    }

    //添加角色
    @RequestMapping("/save.do")
    public String addRole(Role role) throws Exception {
        iRoleService.save(role);
        return "redirect:findAll.do";
    }

    //给角色添加权限
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) String roleId,
                                      @RequestParam(name = "ids", required = true) String[] ids) {

        iRoleService.addPermissionToRole(roleId, ids);


        return "redirect:findAll.do";
    }

    //
}


