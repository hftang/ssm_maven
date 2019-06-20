package com.hftang.ssm.service;

import com.hftang.ssm.domain.Role;
import com.hftang.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-13 10:20
 * @desc
 */
public interface IUserService extends UserDetailsService {

    public List<UserInfo> findAll();


    void save(UserInfo userInfo);

    //查看user详情
    UserInfo findById(String userId);

    List<Role> findOtherRoles(String usrid);

    void addUserToRoles(String userId, String[] roles);
}
