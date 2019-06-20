package com.hftang.ssm.service.impl;

import com.hftang.ssm.dao.IRoleDao;
import com.hftang.ssm.domain.Permission;
import com.hftang.ssm.domain.Role;
import com.hftang.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-17 9:47
 * @desc
 */

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao iRoleDao;

    @Override
    public List<Role> findAll() {


        return iRoleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        iRoleDao.saveRole(role);
    }

    @Override
    public Role findRoleById(String roleId) {
        return iRoleDao.findRoleById(roleId);
    }

    @Override
    public List<Permission> findOtherPermission(String roleId) {
        return iRoleDao.findOtherPermission(roleId);
    }

    //给角色添加权限
    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {

        for (String permissionId : permissionIds) {
            iRoleDao.addPermissionToRole(permissionId,roleId);
        }

    }
}
