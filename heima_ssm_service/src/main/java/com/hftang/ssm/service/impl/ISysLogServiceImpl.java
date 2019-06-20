package com.hftang.ssm.service.impl;

import com.hftang.ssm.dao.ISysLogDao;
import com.hftang.ssm.domain.SysLog;
import com.hftang.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hftang
 * @date 2019-06-19 15:54
 * @desc
 */

@Service
@Transactional
public class ISysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao iSysLogDao;

    @Override
    public void save(SysLog sysLog) throws Exception {
        iSysLogDao.save(sysLog);
    }
}
