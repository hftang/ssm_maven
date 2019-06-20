package com.hftang.ssm.service;

import com.hftang.ssm.domain.SysLog;

/**
 * @author hftang
 * @date 2019-06-19 15:52
 * @desc 保存 日志文件的service
 */
public interface ISysLogService {

    public void save(SysLog sysLog) throws Exception;
}
