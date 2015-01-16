package com.smhdemo.common.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhdemo.common.base.BaseException;
import com.smhdemo.common.security.entity.Permission;
import com.smhdemo.common.security.entity.Role;
import com.smhdemo.common.security.entity.User;
import com.smhdemo.common.security.service.PermissionServiceable;
import com.smhdemo.common.security.service.RoleServiceable;
import com.smhdemo.common.security.service.UserServiceable;

/**
 * 用户功能业务实现统一集
 *  
 * @author zhoudongchu
 */
@Service
public class SecurityFac implements SecurityFacable {
    @Autowired
    private UserServiceable userService;
    @Autowired
    private RoleServiceable roleService;  
    @Autowired
    private PermissionServiceable permissionService;     
	@Override
	public Integer addUser(User vo) throws BaseException{
		return userService.addUser(vo);
	}

	@Override
	public Integer updUser(User vo) throws BaseException{
		return userService.updUser(vo);
	}

	@Override
	public void delUser(int pk) throws BaseException{
		userService.delUser(pk);
	}

	@Override
	public User getUser(int pk) throws BaseException{
		return userService.getUser(pk);
	}

	@Override
	public User getUser(String accountName) throws BaseException {
		
		return userService.getUser(accountName);
	}

	@Override
	public Integer addRole(Role vo) throws BaseException {
		return roleService.addRole(vo);
	}

	@Override
	public Integer updRole(Role vo) throws BaseException {
		return roleService.updRole(vo);
	}

	@Override
	public void delRole(int pk) throws BaseException {
		roleService.delRole(pk);
	}

	@Override
	public Role getRole(int pk) throws BaseException {
		return roleService.getRole(pk);
	}
	@Override
	public List<Role> getAllRole() {
		return roleService.getAllRole();
	}
	
	@Override
	public Integer addPermission(Permission vo) throws BaseException {
		return permissionService.addPermission(vo);
	}

	@Override
	public Integer updPermission(Permission vo) throws BaseException {
		return permissionService.updPermission(vo);
	}

	@Override
	public void delPermission(int pk) throws BaseException {
		permissionService.delPermission(pk);		
	}

	@Override
	public Permission getPermission(int pk) throws BaseException {
		return permissionService.getPermission(pk);
	}
}
