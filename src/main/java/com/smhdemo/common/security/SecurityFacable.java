package com.smhdemo.common.security;

import java.util.List;

import com.smhdemo.common.base.BaseException;
import com.smhdemo.common.security.entity.Permission;
import com.smhdemo.common.security.entity.Role;
import com.smhdemo.common.security.entity.User;

/**
 * 
 *  
 * @author zhoudongchu
 */
public interface SecurityFacable {

	Integer addUser(User vo)throws BaseException;
	Integer updUser(User vo)throws BaseException;
	void delUser(int pk)throws BaseException;
	User getUser(int pk)throws BaseException;
	User getUser(String accountName)throws BaseException;
	
	Integer addRole(Role vo)throws BaseException;
	Integer updRole(Role vo)throws BaseException;
	void delRole(int pk)throws BaseException;
	Role getRole(int pk)throws BaseException;
	public List<Role> getAllRole();
	Integer addPermission(Permission vo)throws BaseException;
	Integer updPermission(Permission vo)throws BaseException;
	void delPermission(int pk)throws BaseException;
	Permission getPermission(int pk)throws BaseException;
}
