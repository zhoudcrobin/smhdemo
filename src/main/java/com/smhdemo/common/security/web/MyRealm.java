package com.smhdemo.common.security.web;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smhdemo.common.security.entity.Role;
import com.smhdemo.common.security.entity.User;
import com.smhdemo.common.security.service.UserServiceable;
import com.smhdemo.common.util.Md5Utils;

/**
 * 
 * 
 * @author zhoudongchu
 */
@Service
@Transactional
public class MyRealm extends AuthorizingRealm {
	private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);
	@Autowired
	private UserServiceable userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principalCollection) {
		try {
			// 获取登录的用户名
			String loginName = (String) principalCollection
					.fromRealm(getName()).iterator().next();
			User user = userService.getUser(loginName);
			if (user != null) {
				SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
				// 登录的用户有多少个角色
				info.setRoles(user.getRolesName());
				List<Role> roleList = user.getRoleList();
				for (Role role : roleList) {
					// 角色有多少个权限
					info.addStringPermissions(role.getPermissionsName());
				}
				return info;
			}
		} catch (Exception e) {
			logger.debug(e.toString());
		}
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		try {
			User user = userService.getUser(token.getUsername());
			String password = new String(token.getPassword());

			if (user != null) {
				if (Md5Utils.checkMd5Password(user.getAccountName(), password,
						user.getSalt(), user.getPassword())) {
					return new SimpleAuthenticationInfo(user.getAccountName(),
							password.toCharArray(), getName());
				}
			}
		} catch (Exception e) {
			logger.debug(e.toString());
		}
		return null;
	}

}
