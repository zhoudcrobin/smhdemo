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
	public Integer addUser(User user) throws BaseException {
		userDao.persist(user);
		return user.getId();
	}

	@Override
	public Integer updUser(User user) throws BaseException {
		userDao.merge(user);
		return user.getId();
	}

	@Override
	public void delUser(int pk) throws BaseException {
		userDao.removeByPK(pk);
	}

	@Override
	public User getUser(String accountName) throws BaseException {
		return userDao.getUser(accountName);
	}

	@Override
	public User getUser(int pk) throws BaseException {
		return userDao.get(pk);
	}
	
	
}
