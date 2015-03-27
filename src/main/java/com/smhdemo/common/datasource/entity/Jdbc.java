/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.smhdemo.common.datasource.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * JDBC数据源
 * 
 * <ul>
 * <li>driver:驱动名</li>
 * <li>connUrl:数据库连接URL</li>
 * <li>userName:用户名</li>
 * <li>passWord:密码</li>
 * </ul>
 * 
 * @author 吴智俊
 */
@Entity
@Table(name = "datasource_jdbc")
@PrimaryKeyJoinColumn(name = "base_id")
public class Jdbc extends Base {

    private static final long serialVersionUID = 7009275702505946488L;
    
    @Column(name = "driver", nullable = false)
    private String driver;
    @Column(name = "connurl", nullable = false)
    private String connUrl;
    @Column(name = "username", nullable = false)
    private String userName;
    @Column(name = "password")
    private String password;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getConnUrl() {
        return connUrl;
    }

    public void setConnUrl(String connUrl) {
        this.connUrl = connUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
