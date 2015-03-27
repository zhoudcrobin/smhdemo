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
import com.smhdemo.common.report.entity.Text;


/**
 * 
 * @author wu_zhijun
 *
 */
@Repository
public class TextDao extends JpaDao<Long, Text> {
	
	public List<Category> findCategoryReportByTextReportId(final Long textReportId){
		String hql = "Select c From Category As c Left Join c.textReports As t Where t.id=:textReportId";
		
		TypedQuery<Category> query = this.getEntityManager().createQuery(hql, Category.class);
		query.setParameter("textReportId", textReportId);
		
		return query.getResultList();
	}
	
//	public List<EwcmsJobReport> findEwcmsJobReportByTextReportId(final Long textReportId){
//		String hql = "Select e From EwcmsJobReport As e Where e.textReport.id=:textReportId";
//		
//		TypedQuery<EwcmsJobReport> query = this.getEntityManager().createQuery(hql, EwcmsJobReport.class);
//		query.setParameter("textReportId", textReportId);
//		
//		return query.getResultList();
//	}
}
