package com.hftang.ssm.dao;

import com.hftang.ssm.domain.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-17 11:01
 * @desc
 */
public interface PermissionDao {
    @Select("select * from permission")
    public List<Permission> findAll();
}
