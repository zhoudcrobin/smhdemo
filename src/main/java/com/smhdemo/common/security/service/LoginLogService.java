package com.smhdemo.common.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhdemo.common.security.dao.LoginLogDao;
import com.smhdemo.common.security.entity.LoginLog;

/**
 * 
 *  
 * @author zhoudongchu
 */
@Service
public class LoginLogService implements LoginLogServiceable {
	@Autowired
	protected LoginLogDao loginLogDao;
	@Override
	public Integer addLoginLog(LoginLog vo) {
		loginLogDao.persist(vo);
		return vo.getId();
	}

}

