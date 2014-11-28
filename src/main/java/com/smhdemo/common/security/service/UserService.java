package com.smhdemo.common.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhdemo.common.base.BaseException;
import com.smhdemo.common.security.dao.UserDaoable;
import com.smhdemo.common.security.entity.User;
/**
 * 
 *  
 * @author zhoudongchu
 */
@Service
public class UserService implements UserServiceable {
	@Autowired
	protected UserDaoable userDao;
	
	@Override
	public String addUser(User user) throws BaseException {
		userDao.persist(user);
		return user.getAccountName();
	}

	@Override
	public String updUser(User user) throws BaseException {
		userDao.merge(user);
		return user.getAccountName();
	}

	@Override
	public void delUser(String accountName) throws BaseException {
		userDao.removeByPK(accountName);
	}

	@Override
	public User getUser(String accountName) throws BaseException {
		return userDao.get(accountName);
	}
}
