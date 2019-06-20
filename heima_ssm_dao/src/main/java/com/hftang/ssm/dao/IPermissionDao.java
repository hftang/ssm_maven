package com.hftang.ssm.dao;

import com.hftang.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-14 10:32
 * @desc 权限的dao
 */
public interface IPermissionDao {


    @Select("select * from permission where id in ( select permissionId from role_permission where roleId = #{roleId})")
    public List<Permission> findPermissionByRoleId(String roleId) throws Exception;

    @Select("select * from permission")
    public List<Permission> findAll() throws Exception;

    @Insert("insert into permission (permissionName,url) values( #{permissionName},#{url})")
    public void save(Permission permission);
}
