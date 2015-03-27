/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.smhdemo.common.datasource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhdemo.common.datasource.dao.BaseDao;
import com.smhdemo.common.datasource.entity.Base;


@Service
public class BaseService implements BaseServiceable {
	
	@Autowired
	private BaseDao baseDAO;

	@Override
	public Long addBase(Base vo) {
		baseDAO.persist(vo);
		return vo.getId();
	}

	@Override
	public Long updBase(Base vo) {
		baseDAO.merge(vo);
		return vo.getId();
	}

	@Override
	public Base getBase(Long pk) {
		return baseDAO.get(pk);
	}

	@Override
	public List<Base> findAllBase() {
		return baseDAO.findAll();
	}

	@Override
	public void deletedBase(Long pk) {
		baseDAO.removeByPK(pk);
	}
}
