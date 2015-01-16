package com.smhdemo.common.security.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONType;
/**
 * 
 *  
 * @author zhoudongchu
 */
@Entity
@Table(name="shiro_role")
@SequenceGenerator(name = "seq_shiro_role", sequenceName = "seq_shiro_role_id", allocationSize = 1)
@JSONType(ignores = { "userList", "permissionList"}) 
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "seq_shiro_role", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name="rolename",length=50)
	private String roleName;
	
	private String remark;
	@ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinTable(name = "shiro_user_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> userList; //一个角色对应多个用户
    
    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
	private List<Permission> permissionList; //一个角色对应多个权限

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}
	
	@Transient
	public List<String> getPermissionsName(){
		List<String> list=new ArrayList<String>();
		List<Permission> perlist=getPermissionList();
		for (Permission per : perlist) {
			list.add(per.getPermissionName());
		}
		return list;
	}
}
