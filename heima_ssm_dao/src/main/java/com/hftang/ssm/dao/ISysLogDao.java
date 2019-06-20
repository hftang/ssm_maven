package com.hftang.ssm.dao;

import com.hftang.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;

/**
 * @author hftang
 * @date 2019-06-19 15:56
 * @desc 保存日志
 */

public interface ISysLogDao {



    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values ( #{visitTime}, #{username}, #{ip}, #{url}, #{executionTime}, #{method})")
    public void save(SysLog log) throws Exception;
}
