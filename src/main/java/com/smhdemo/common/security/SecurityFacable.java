package com.smhdemo.common.security;

import com.smhdemo.common.base.BaseException;
import com.smhdemo.common.security.entity.User;

/**
 * 
 *  
 * @author zhoudongchu
 */
public interface SecurityFacable {

	String addUser(User vo)throws BaseException;
	String updUser(User vo)throws BaseException;
	void delUser(String pk)throws BaseException;
	User getUser(String pk)throws BaseException;
}
