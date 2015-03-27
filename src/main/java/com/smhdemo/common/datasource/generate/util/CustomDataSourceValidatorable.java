/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.smhdemo.common.datasource.generate.util;

import org.springframework.validation.Errors;

import com.smhdemo.common.datasource.entity.Custom;

public interface CustomDataSourceValidatorable {
	void validatePropertyValues(Custom ds, Errors errors);
}
