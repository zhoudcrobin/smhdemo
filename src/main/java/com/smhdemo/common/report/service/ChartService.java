/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.smhdemo.common.report.service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.smhdemo.common.base.BaseException;
import com.smhdemo.common.report.dao.CategoryDao;
import com.smhdemo.common.report.dao.ChartDao;
import com.smhdemo.common.report.dao.ParameterDao;
import com.smhdemo.common.report.entity.Category;
import com.smhdemo.common.report.entity.Chart;
import com.smhdemo.common.report.entity.Parameter;
import com.smhdemo.common.report.entity.Text;
import com.smhdemo.common.report.util.ChartAnalysisUtil;
/**
 * 
 * @author zhoudongchu
 *
 */
@Service
public class ChartService implements ChartServiceable {

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ChartDao chartDao;
	@Autowired
	private ParameterDao parameterDao;
	
	@Override
	public Long addChart(Chart vo) {
		Assert.notNull(vo);
		Assert.hasLength(vo.getChartSql());

		Set<Parameter> parameters = ChartAnalysisUtil.analysisSql(vo.getChartSql());
		vo.setParameters(parameters);

		chartDao.persist(vo);
		return vo.getId();
	}

	@Override
	public Long updChart(Chart vo) {
		Assert.notNull(vo);
		Assert.hasLength(vo.getChartSql());
		
		Chart entity = chartDao.get(vo.getId());
		
		entity.setName(vo.getName());
		entity.setBaseDS(vo.getBaseDS());
		entity.setType(vo.getType());
		entity.setShowTooltips(vo.getShowTooltips());
		entity.setChartTitle(vo.getChartTitle());
		entity.setFontName(vo.getFontName());
		entity.setFontSize(vo.getFontSize());
		entity.setFontStyle(vo.getFontStyle());
		entity.setHorizAxisLabel(vo.getHorizAxisLabel());
		entity.setVertAxisLabel(vo.getVertAxisLabel());
		entity.setDataFontName(vo.getDataFontName());
		entity.setDataFontSize(vo.getDataFontSize());
		entity.setDataFontStyle(vo.getDataFontStyle());
		entity.setAxisFontName(vo.getAxisFontName());
		entity.setAxisFontSize(vo.getAxisFontSize());
		entity.setAxisFontStyle(vo.getAxisFontStyle());
		entity.setAxisTickFontName(vo.getAxisTickFontName());
		entity.setAxisTickFontSize(vo.getAxisTickFontSize());
		entity.setAxisTickFontStyle(vo.getAxisTickFontStyle());
		entity.setTickLabelRotate(vo.getTickLabelRotate());
		entity.setShowLegend(vo.getShowLegend());
		entity.setLegendPosition(vo.getLegendPosition());
		entity.setLegendFontName(vo.getLegendFontName());
		entity.setLegendFontSize(vo.getLegendFontSize());
		entity.setLegendFontStyle(vo.getLegendFontStyle());
		entity.setChartHeight(vo.getChartHeight());
		entity.setChartWidth(vo.getChartWidth());
		entity.setBgColorB(vo.getBgColorB());
		entity.setBgColorG(vo.getBgColorG());
		entity.setBgColorR(vo.getBgColorR());
		entity.setRemark(vo.getRemark());
		entity.setUpdateDate(new Date(Calendar.getInstance().getTime().getTime()));
		
		if (!entity.getChartSql().equals(vo.getChartSql())) {
			entity.setChartSql(vo.getChartSql());
			Set<Parameter> oldParameters = entity.getParameters();
			oldParameters.clear();
			Set<Parameter> newParameters = ChartAnalysisUtil.analysisSql(vo.getChartSql());
			for (Parameter newParameter : newParameters){
				oldParameters.add(newParameter);
			}	
			entity.setParameters(oldParameters);
		}
		chartDao.merge(entity);
		return vo.getId();
	}

	@Override
	public void delChart(Long pk) {
		Chart chartReport = chartDao.get(pk);
		Assert.notNull(chartReport);
		List<Category> categories = chartDao.findCategoryReportByChartReportId(pk);
		if (categories != null && !categories.isEmpty()){
			for (Category category : categories){
				Set<Chart> charts = category.getChartReports();
				if (charts.isEmpty()) continue;
				charts.remove(chartReport);
				category.setChartReports(charts);
				categoryDao.merge(category);
			}
		}
//		List<EwcmsJobReport> ewcmsJobReports = chartDao.findEwcmsJobReportByChartReportId(chartReportId);
//		if (ewcmsJobReports != null && !ewcmsJobReports.isEmpty()){
//			for (EwcmsJobReport ewcmsJobReport : ewcmsJobReports){
//				if (ewcmsJobReport.getTextReport() == null) {
//					ewcmsJobReportDAO.remove(ewcmsJobReport);
//				}else{
//					ewcmsJobReport.setChartReport(null);
//					ewcmsJobReportDAO.merge(ewcmsJobReport);
//				}
//			}
//		}
		chartDao.removeByPK(pk);
	}

	@Override
	public Chart getChart(Long pk) {
		return chartDao.get(pk);
	}

	@Override
	public List<Chart> findAllChart() {
		return chartDao.findAll();
	}

	@Override
	public void updChartParameter(List<Parameter> parameters)
			throws BaseException {
		for(Parameter vo:parameters){
			Parameter oldvo = parameterDao.get(vo.getId());
			oldvo.setCnName(vo.getCnName());
			oldvo.setDefaultValue(vo.getDefaultValue());
			oldvo.setType(vo.getType());
			oldvo.setValue(vo.getValue());
			parameterDao.merge(oldvo);
		}
	}
}
