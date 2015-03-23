/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.smhdemo.common.report.service;

import java.util.List;

import com.smhdemo.common.base.BaseException;
import com.smhdemo.common.report.entity.Chart;
import com.smhdemo.common.report.entity.Parameter;

/**
 * 图型报表服务接口
 * 
 * @author zhoudongchu
 */
public interface ChartServiceable {
	/**
	 * 新增图型报表并把图型报表中的参数存入数据库中
	 * 
	 * @param chartReport 图型报表对象
	 * @return Long 图型报表编号
	 */
	public Long addChart(Chart vo);
	
	/**
	 * 修改图型报表
	 * 
	 * @param chartReport 图型报表对象
	 * @return Long 图型报表编号
	 */
	public Long updChart(Chart vo);

	/**
	 * 删除图型报表对象
	 * 
	 * @param chartReportId 图型报表编号
	 */
	public void delChart(Long pk);
	
	/**
	 * 查询图型报表对象
	 * 
	 * @param chartReportId 图型报表编号
	 * @return ChartReport 图型报表对象
	 */
	public Chart getChart(Long pk);

	/**
	 * 查询所有图型报表
	 * 
	 * @return List 图型报表集合
	 */
	public List<Chart> findAllChart();
	
	/**
	 * 更新图型参数
	 * 
	 * @param chartReportId 图型编号
	 * @param pagesList 页面参数集合
	 * @throws BaseException
	 */
	public Long updChartParameter(Long chartId,Parameter parameter) throws BaseException;
}
