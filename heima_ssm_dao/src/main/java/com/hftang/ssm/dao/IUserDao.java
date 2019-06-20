package com.hftang.ssm.dao;

import com.hftang.ssm.domain.Role;
import com.hftang.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author hftang
 * @date 2019-06-13 10:32
 * @desc
 */
public interface IUserDao {

    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.hftang.ssm.dao.IRoleDao.findRoleByUserIds")),
    })
    public UserInfo findUserByName(String username) throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users (email,username,password,phoneNum,status) values (#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Select("select * from users where id = #{userId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.hftang.ssm.dao.IRoleDao.findRoleByUserIds"))
    })
    UserInfo findById(String userId);

    //查找其他
    @Select("select * from role where id not in ( select roleId from users_role where userId = #{usrid})")
    List<Role> findOtherRoles(String usrid);

    //给用户添加角色
    @Insert("insert into users_role(userId,roleId) values ( #{userId},#{roleId})")
    void addUserToRole(@Param("userId") String userId, @Param("roleId") String roleId);
}
