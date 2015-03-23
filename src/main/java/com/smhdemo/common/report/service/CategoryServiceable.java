/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.smhdemo.common.report.service;

import java.util.List;

import com.smhdemo.common.report.entity.Category;

/**
 * 报表分类管理服务接口
 * 
 * @author zhoudongchu
 */
public interface CategoryServiceable {
	/**
	 * 新增报表分类对象
	 * 
	 * @param categoryReport 分类对象
	 */
	public Long addCategory(Category vo);
	/**
	 * 修改报表分类对象
	 * 
	 * @param categoryReport 分类对象
	 */
	public Long updCategory(Category vo);
	/**
	 * 删除报表分类对象
	 * 
	 * @param categoryReportId 分类编号
	 */
	public void delCategory(Long pk);
	
	/**
	 * 查询报表分类对象
	 * 
	 * @param categoryReportId 分类编号
	 * @return Categories 对象
	 */
	public Category getCategory(Long pk);

	/**
	 * 查询所有分类对象
	 * 
	 * @return List对象
	 */
	public List<Category> findAllCategory();

	/**
	 * 根据分类编号保存报表
	 * 
	 * @param categoryReportId 分类编号
	 * @param textIds 报表编号数组
	 */
	public void addTextToCategory(Long categoryId, Long[] textIds);

	/**
	 * 根据分类编号保存图型
	 * 
	 * @param categoryReportId 分类编号
	 * @param chartIds 图型编号数组
	 */
	public void addChartToCategory(Long categoryId,Long[] chartIds);
	
	/**
	 * 判断文字报表是否在分类中
	 * 
	 * @param textId 文字报表编号
	 * @param categoryId 分类编号
	 * @return Boolean (false:不存在,true:存在)
	 */
	public Boolean findTextIsEntityByTextAndCategory(Long textId, Long categoryId);
	
	/**
	 * 判断图型报表是否在分类中
	 * 
	 * @param chartId 图型报表编号
	 * @param categoryId 分类编号
	 * @return Boolean (false:不存在,true:存在)
	 */
	public Boolean findChartIsEntityByChartAndCategory(Long chartId, Long categoryId);
}
