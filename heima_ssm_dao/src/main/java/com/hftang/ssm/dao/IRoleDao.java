package com.hftang.ssm.dao;

import com.hftang.ssm.domain.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-13 14:37
 * @desc 角色的dao
 */
public interface IRoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.hftang.ssm.dao.IPermissionDao.findPermissionByRoleId"))

    })
    public List<Role> findRoleByUserIds(String userId) throws Exception;
}
