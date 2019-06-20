package com.hftang.ssm.service.impl;

import com.hftang.ssm.dao.IPermissionDao;
import com.hftang.ssm.domain.Permission;
import com.hftang.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-17 11:00
 * @desc
 */

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private IPermissionDao iPermissionDao;


    @Override
    public List<Permission> findAll() throws Exception {
        return iPermissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        iPermissionDao.save(permission);
    }
}
