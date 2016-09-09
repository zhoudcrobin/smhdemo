/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
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
import com.alibaba.fastjson.annotation.JSONField;

@Entity
@Table(name="shiro_loginlog")
@SequenceGenerator(name = "seq_shiro_loginlog", sequenceName = "seq_shiro_loginlog_id", allocationSize = 1)
public class LoginLog implements Serializable {
	private static final long serialVersionUID = 1578530886376782729L;
	@Id
	@GeneratedValue(generator = "seq_shiro_loginlog", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "ipaddress", length = 30)
	private String ipAddress; 
	
	@JSONField (format="yyyy-MM-dd HH:mm:ss")  
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "logintime", nullable = false)
	private Date loginTime = new Date();
	
	@Column(name = "accountname", nullable = false, length = 20)
	private String accountName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}



	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginLog other = (LoginLog) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
