/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.smhdemo.common.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhdemo.common.report.dao.ParameterDao;
import com.smhdemo.common.report.entity.Parameter;
/**
 * 
 * @author zhoudongchu
 *
 */
@Service
public class ParameterService implements ParameterServiceable {

	@Autowired
	private ParameterDao parameterDao;
	
	@Override
	public Parameter getParameter(Long pk) {
		return parameterDao.get(pk);
	}


	@Override
	public Boolean findSessionIsEntityByParameterIdAndUserName(Long parameterId, String userName) {
		return parameterDao.findSessionIsEntityByParameterIdAndUserName(parameterId, userName);
	}
}
