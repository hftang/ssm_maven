package com.hftang.ssm.service;

import com.hftang.ssm.domain.Permission;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-17 10:59
 * @desc
 */
public interface PermissionService {

    public List<Permission> findAll() throws Exception;

    void save(Permission permission);
}
