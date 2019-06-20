package com.hftang.ssm.service;

import com.hftang.ssm.domain.Permission;
import com.hftang.ssm.domain.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-17 9:45
 * @desc
 */

public interface IRoleService {


    public List<Role> findAll();

    public void save(Role role) throws Exception;

    Role findRoleById(String roleId);

    List<Permission> findOtherPermission(String roleId);

    public void addPermissionToRole(String roleId, String[] ids);

}
