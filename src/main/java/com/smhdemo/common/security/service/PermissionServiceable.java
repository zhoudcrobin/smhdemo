package com.smhdemo.common.security.service;

import com.smhdemo.common.base.BaseException;
import com.smhdemo.common.security.entity.Permission;

/**
 * 
 *  
 * @author zhoudongchu
 */
public interface PermissionServiceable {
	Integer addPermission(Permission vo)throws BaseException;;
	Integer updPermission(Permission vo)throws BaseException;;
	void delPermission(int pk)throws BaseException;;
	Permission getPermission(int pk)throws BaseException;;
}

