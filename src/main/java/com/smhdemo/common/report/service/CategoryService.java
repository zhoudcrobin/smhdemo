/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.smhdemo.common.report.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smhdemo.common.report.dao.CategoryDao;
import com.smhdemo.common.report.dao.ChartDao;
import com.smhdemo.common.report.dao.TextDao;
import com.smhdemo.common.report.entity.Category;
import com.smhdemo.common.report.entity.Chart;
import com.smhdemo.common.report.entity.Text;

/**
 * 
 * @author zhoudongchu
 * 
 */
@Service
public class CategoryService implements CategoryServiceable {

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private TextDao textDao;
	@Autowired
	private ChartDao chartDao;

	@Override
	public Long addCategory(Category vo) {
		categoryDao.persist(vo);
		return vo.getId();
	}

	@Override
	public Long updCategory(Category vo) {
		Long categoryId = vo.getId();
		Category source = categoryDao.get(categoryId);
		if (source.getChartReports() != null
				&& !source.getChartReports().isEmpty()) {
			vo.setChartReports(source.getChartReports());
		}
		if (source.getTextReports() != null
				&& !source.getTextReports().isEmpty()) {
			vo.setTextReports(source.getTextReports());
		}
		categoryDao.merge(vo);

		return vo.getId();
	}

	@Override
	public void delCategory(Long pk) {
		categoryDao.removeByPK(pk);
	}

	@Override
	public Category getCategory(Long pk) {
		return categoryDao.get(pk);
	}

	@Override
	public List<Category> findAllCategory() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

	@Override
	public void addTextToCategory(Long categoryId, Long[] textIds) {
		Category category = getCategory(categoryId);

		Set<Text> texts = new HashSet<Text>();
		if (textIds != null && textIds.length > 0) {
			for (int i = 0; i < textIds.length; i++) {
				Long reportId = textIds[i];
				Text text = textDao.get(reportId);
				texts.add(text);
			}
		}
		category.setTextReports(texts);
		categoryDao.merge(category);

	}

	@Override
	public void addChartToCategory(Long categoryId, Long[] chartIds) {
		Category category = getCategory(categoryId);

		Set<Chart> chartList = new HashSet<Chart>();
		if (chartIds != null && chartIds.length > 0) {
			for (int i = 0; i < chartIds.length; i++) {
				Long chartId = chartIds[i];
				Chart chart = chartDao.get(chartId);
				chartList.add(chart);
			}
		}
		category.setChartReports(chartList);
		categoryDao.merge(category);
	}

	@Override
	public Boolean findTextIsEntityByTextAndCategory(Long textId,
			Long categoryId) {
		return categoryDao
				.findTextIsEntityByTextAndCategory(textId, categoryId);
	}

	@Override
	public Boolean findChartIsEntityByChartAndCategory(Long chartId,
			Long categoryId) {
		return categoryDao.findChartIsEntityByChartAndCategory(chartId,
				categoryId);
	}
}
