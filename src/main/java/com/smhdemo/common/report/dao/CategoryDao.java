/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.smhdemo.common.report.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.smhdemo.common.base.JpaDao;
import com.smhdemo.common.report.entity.Category;

/**
 * 
 * @author wu_zhijun
 *
 */
@Repository
public class CategoryDao extends JpaDao<Long, Category> {
	
	public Boolean findTextIsEntityByTextAndCategory(final Long textReportId, final Long categoryReportId){
    	String hql = "Select c From Category As c Left Join c.texts As t Where t.id=:textReportId And c.id=:categoryReportId";
    	
    	TypedQuery<Category> query = this.getEntityManager().createQuery(hql, Category.class);
    	query.setParameter("textReportId", textReportId);
    	query.setParameter("categoryReportId", categoryReportId);
    	
    	List<Category> list = query.getResultList();
    	return list.isEmpty() ? false : true;
    }
	
	public Boolean findChartIsEntityByChartAndCategory(final Long chartReportId, final Long categoryReportId){
    	String hql = "Select c From Category As c Left Join c.charts As t Where t.id=:chartReportId And c.id=:categoryReportId";
    	
    	TypedQuery<Category> query = this.getEntityManager().createQuery(hql, Category.class);
    	query.setParameter("chartReportId", chartReportId);
    	query.setParameter("categoryReportId", categoryReportId);
    	
    	List<Category> list = query.getResultList();
    	return list.isEmpty() ? false : true;
    }
}
