package com.smhdemo.common.security.service;

import java.util.List;

import com.smhdemo.common.base.BaseException;
import com.smhdemo.common.security.entity.Role;

/**
 * 
 *  
 * @author zhoudongchu
 */
public interface RoleServiceable {
	Integer addRole(Role vo)throws BaseException;
	Integer updRole(Role vo)throws BaseException;
	void delRole(int pk)throws BaseException;
	Role getRole(int pk)throws BaseException;
	public List<Role> getAllRole();
	
}
