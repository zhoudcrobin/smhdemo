/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.smhdemo.common.datasource.generate.factory;

import com.smhdemo.common.datasource.entity.Base;
import com.smhdemo.common.datasource.generate.service.DataSourceServiceable;


/**
 * 数据源工厂接口
 *
 * @author 吴智俊
 */
public interface DataSourceFactoryable {

	/**
	 * 建立数据源服务
	 * 
	 * @param dataSource 
	 * @return AlqcDataSourceServiceable
	 */
    public DataSourceServiceable createService(Base dataSource);
}
