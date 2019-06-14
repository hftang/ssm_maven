package com.hftang.ssm.service.impl;

import com.hftang.ssm.dao.IUserDao;
import com.hftang.ssm.domain.Role;
import com.hftang.ssm.domain.UserInfo;
import com.hftang.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hftang
 * @date 2019-06-13 10:21
 * @desc
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao iUserDao;

    //springsecurity 提供的加密方法
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = iUserDao.findUserByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //这个User 是由springsecurity提供的
        //User user = new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(), getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role item : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + item.getRoleName()));
        }
        return list;
    }

    //查到所有用户
    @Override
    public List<UserInfo> findAll() {
        List<UserInfo> users = iUserDao.findAll();
        return users;
    }

    //保存用户
    @Override
    public void save(UserInfo userInfo) {
        //对密码进行加密处理
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        iUserDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String userId) {

        return iUserDao.findById(userId);
    }
}
