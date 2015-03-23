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
import com.smhdemo.common.report.entity.Chart;

/**
 * 
 * @author wu_zhijun
 *
 */
@Repository
public class ChartDao extends JpaDao<Long, Chart> {
	
	public List<Category> findCategoryReportByChartReportId(final Long chartReportId){
		String hql = "Select c From Category As c Left Join c.charts As t Where t.id=:chartReportId";
		
		TypedQuery<Category> query = this.getEntityManager().createQuery(hql, Category.class);
		query.setParameter("chartReportId", chartReportId);
		
		return query.getResultList();
	}
	
//	public List<EwcmsJobReport> findEwcmsJobReportByChartReportId(final Long chartReportId){
//		String hql = "Select e From EwcmsJobReport As e Where e.chartReport.id=:chartReportId";
//		
//		TypedQuery<EwcmsJobReport> query = this.getEntityManager().createQuery(hql, EwcmsJobReport.class);
//		query.setParameter("chartReportId", chartReportId);
//		
//		return query.getResultList();
//	}
}
