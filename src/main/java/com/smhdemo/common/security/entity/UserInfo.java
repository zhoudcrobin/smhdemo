package com.smhdemo.common.security.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 
 * <ul>
 * <li>userID:账户名
 * <li>birthday:生日
 * <li>realName:用户真实姓名
 * <li>email:邮件
 * <li>phone:电话
 * </ul>
 * @author zhoudongchu
 */
@Entity
@Table(name="shiro_userinfo")
@SequenceGenerator(name = "seq_shiro_userinfo", sequenceName = "seq_shiro_userinfo_id", allocationSize = 1)
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seq_shiro_userinfo", strategy = GenerationType.SEQUENCE)
    private Integer id;
	
	@JSONField (format="yyyy-MM-dd") 
	@DateTimeFormat(pattern="yyyy-MM-dd") 
    @Temporal(TemporalType.DATE)
    private Date birthday;	
    
    @Column(name="realname",length=32)
    private String realName;
    
    @Column(length=50)
    private String email;
    
    @Column(length=50)
    private String phone;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}   

    
}

