package com.smhdemo.common.security.dao;

import java.util.List;

import com.smhdemo.common.base.JpaDaoable;
import com.smhdemo.common.security.entity.Role;

/**
 * 
 *  
 * @author zhoudongchu
 */
public interface RoleDaoable extends JpaDaoable<Integer, Role> {
	List<Role> getAllRole();
}

