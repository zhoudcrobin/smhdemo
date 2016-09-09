package com.smhdemo.common.security.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
import com.alibaba.fastjson.annotation.JSONType;
/**
 * <ul>
 * <li>accountName:账户名
 * <li>password:密码
 * <li>enabled:是否可用(true:可用)
 * <li>userInfo:用户信息
 * <li>roleList:所属角色
 * </ul>
 *
 * @author zhoudongchu
 */
@Entity
@Table(name="shiro_user")
@SequenceGenerator(name = "seq_shiro_user", sequenceName = "seq_shiro_user_id", allocationSize = 1)
@JSONType(ignores = { "roleList" }) 
public class User implements Serializable {
	private static final long serialVersionUID = -6551089922634688550L;
	@Id
	@GeneratedValue(generator = "seq_shiro_user", strategy = GenerationType.SEQUENCE)
	private Integer id;
    
    @Column(name="accountname",length=32)
    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9]{5,20}", message = "账户名是由数字字母组成的5到20位的字符串") 
    private String accountName;
    @Column(name="salt",length=100)
    private String salt;
    private String password;
    private Boolean enabled = true;
    @OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinColumn(name="userinfo_id",nullable=true)
    private UserInfo userInfo;

	@ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinTable(name = "shiro_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roleList ;//一个用户具有多个角色
    public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Transient
	public Set<String> getRolesName(){
		List<Role> roles=getRoleList();
		Set<String> set=new HashSet<String>();
		for (Role role : roles) {
			set.add(role.getRoleName());
		}
		return set;
	}
    
}
