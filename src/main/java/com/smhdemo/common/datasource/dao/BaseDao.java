/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.smhdemo.common.datasource.dao;

import org.springframework.stereotype.Repository;

import com.smhdemo.common.base.JpaDao;
import com.smhdemo.common.datasource.entity.Base;

/**
 * 
 * @author wu_zhijun
 *
 */
@Repository
public class BaseDao extends JpaDao<Long, Base> {

}
