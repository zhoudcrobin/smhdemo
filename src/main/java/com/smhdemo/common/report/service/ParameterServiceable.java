/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.smhdemo.common.report.service;

import com.smhdemo.common.report.entity.Parameter;

/**
 * @author zhoudongchu
 */
public interface ParameterServiceable {
	
	/**
	 * 查询参数对象
	 * 
	 * @param parameterId 参数编号
	 * @return Parameter 参数对象
	 */
	public Parameter getParameter(Long pk);
	
	/**
	 * 查询Session组用是否设置参数
	 * 
	 * @param parameterId
	 * @param userName
	 * @return
	 */
	public Boolean findSessionIsEntityByParameterIdAndUserName(Long parameterId, String userName);
}
