package com.smhdemo.common.security.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 *  
 * @author zhoudongchu
 */
@Entity
@Table(name="shiro_permission")
@SequenceGenerator(name = "seq_shiro_permission", sequenceName = "seq_shiro_permission_id", allocationSize = 1)
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "seq_shiro_permission", strategy = GenerationType.SEQUENCE)	
	private Integer id;
	
	@Column(name="permissionname",length=50)
	private String permissionName;
	
	private String Remark;
    @ManyToOne(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id",nullable=true)	
	private Role role; //一个权限对应一个角色
    
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
