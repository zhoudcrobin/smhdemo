/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.smhdemo.common.datasource.service;

import java.util.List;

import com.smhdemo.common.datasource.entity.Base;

public interface BaseServiceable {
	/**
	 * 新增数据源
	 * 
	 * @param baseDS ReportDataSource对象
	 * @return Long 数据源对象编号
	 */
	public Long addBase(Base vo);
	
	/**
	 * 修改数据源
	 * 
	 * @param baseDS ReportDataSource对象
	 * @return Long 数据源对象编号
	 */
	public Long updBase(Base vo);
	/**
	 * 查询数据源对象
	 * 
	 * @param baseDSId 数据源编号
	 * @return ReportDataSource对象
	 */
	public Base getBase(Long pk);

	/**
	 * 查询所有数据源对象
	 * 
	 * @return List对象
	 */
	public List<Base> findAllBase();

	/**
	 * 删除数据源对象
	 * 
	 * @param baseDSId 数据源编号
	 */
	public void deletedBase(Long pk);
}
