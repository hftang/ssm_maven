package com.hftang.ssm.dao;

import com.hftang.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @author hftang
 * @date 2019-06-12 15:17
 * @desc
 */
public interface IMemberDao {

    //根据id 查找会员信息
    @Select("select * from member where id = #{id}")
    public Member findById(String id) throws Exception;
}
