package com.hftang.ssm.dao;

import com.hftang.ssm.domain.Permission;
import com.hftang.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-13 14:37
 * @desc 角色的dao
 */
public interface IRoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.hftang.ssm.dao.IPermissionDao.findPermissionByRoleId"))

    })
    public List<Role> findRoleByUserIds(String userId) throws Exception;

    //查询所有
    @Select("select * from role")
    public List<Role> findAll();

    @Insert("insert into role( roleName,roleDesc ) value( #{roleName},#{roleDesc})")
    public void saveRole(Role role) throws Exception;

    @Select("select * from role where id= #{roleId}")
    Role findRoleById(String roleId);

    @Select("select * from permission where id not in ( select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermission(String roleId);

    @Insert("insert into role_permission values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("permissionId") String permissionId, @Param("roleId") String roleId);
}
