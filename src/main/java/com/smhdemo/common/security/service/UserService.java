package com.smhdemo.common.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhdemo.common.base.BaseException;
import com.smhdemo.common.security.dao.UserDao;
import com.smhdemo.common.security.entity.User;
import com.smhdemo.common.util.Md5Utils;
/**
 * 
 *  
 * @author zhoudongchu
 */
@Service
public class UserService implements UserServiceable {
	@Autowired
	protected UserDao userDao;
	
	@Override
	public Integer addUser(User user) throws BaseException {
		User md5User = Md5Utils.md5Password(user.getAccountName(), user.getPassword());
		user.setPassword(md5User.getPassword());
		user.setSalt(md5User.getSalt());
		userDao.persist(user);
		return user.getId();
	}

	@Override
	public Integer updUser(User user) throws BaseException {
		User md5User = Md5Utils.md5Password(user.getAccountName(), user.getPassword());
		user.setPassword(md5User.getPassword());
		user.setSalt(md5User.getSalt());
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
