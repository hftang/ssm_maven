package com.hftang.ssm.controller;

import com.hftang.ssm.domain.Role;
import com.hftang.ssm.domain.UserInfo;
import com.hftang.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-13 16:31
 * @desc
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    //查询所有用户
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> list = iUserService.findAll();
        mv.addObject("userList", list);
        mv.setViewName("user-list");

        return mv;
    }

    //保存用户
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) {
        iUserService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id") String userId) {
        ModelAndView mv = new ModelAndView();

        UserInfo userInfo = iUserService.findById(userId);

        mv.setViewName("user-show");
        mv.addObject("user", userInfo);

        return mv;
    }

    //查询用户以及用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String usrid) {
        ModelAndView mv = new ModelAndView();
        //根据id查询用户
        UserInfo userInfo = iUserService.findById(usrid);
        //根据用户id 查询所能添加的权限
        List<Role> otherRoles = iUserService.findOtherRoles(usrid);

        mv.addObject("user", userInfo);
        mv.addObject("roleList", otherRoles);

        mv.setViewName("user-role-add");
        return mv;
    }

    //添加用户角色
    @RequestMapping("/addRoleToUser.do")
    public String addUserToRoles(@RequestParam(name = "userId", required = true) String userId,
                                 @RequestParam(name = "ids", required = true) String[] roles) {

        iUserService.addUserToRoles(userId,roles);




        return "redirect:findAll.do";

    }
}
