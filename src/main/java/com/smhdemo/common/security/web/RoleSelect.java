package com.smhdemo.common.security.web;

import java.io.Serializable;
import java.util.List;

/**
 * 
 *  
 * @author zhoudongchu
 */
public class RoleSelect implements Serializable {
    private List<String> roles;

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
