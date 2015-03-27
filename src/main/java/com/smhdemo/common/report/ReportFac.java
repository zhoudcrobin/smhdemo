package com.smhdemo.common.report;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhdemo.common.base.BaseException;
import com.smhdemo.common.report.entity.Category;
import com.smhdemo.common.report.entity.Chart;
import com.smhdemo.common.report.entity.Parameter;
import com.smhdemo.common.report.entity.Text;
import com.smhdemo.common.report.service.CategoryServiceable;
import com.smhdemo.common.report.service.ChartServiceable;
import com.smhdemo.common.report.service.ParameterServiceable;
import com.smhdemo.common.report.service.TextServiceable;

/**
 * 
 *  
 * @author zhoudongchu
 */
@Service
public class ReportFac {
	@Autowired 
	CategoryServiceable categoryService;
	@Autowired 
	ChartServiceable chartService;
	@Autowired 
	ParameterServiceable parameterService;
	@Autowired 
	TextServiceable textService;

	public Long addChart(Chart vo) {
		return chartService.addChart(vo);
	}

	public Long updChart(Chart vo){
		return chartService.updChart(vo);
	}

	public void delChart(Long pk) {
		chartService.delChart(pk);
	}

	public Chart getChart(Long pk){
		return chartService.getChart(pk);
	}

	public List<Chart> findAllChart() {
		return chartService.findAllChart();
	}
	
	public void updChartParameter( List<Parameter> parameters)
			throws BaseException{
		chartService.updChartParameter(parameters);
	}
	public Long addCategory(Category vo) {
		return categoryService.addCategory(vo);
	}
	
	public Long updCategory(Category vo) {
		return categoryService.updCategory(vo);
	}
	
	public void delCategory(Long pk){
		categoryService.delCategory(pk);
	}

	public Category getCategory(Long pk) {
		return categoryService.getCategory(pk);
	}

	public List<Category> findAllCategory(){
		return categoryService.findAllCategory();
	}

	public void addTextToCategory(Long categoryId, Long[] textIds){
		categoryService.addTextToCategory(categoryId, textIds);
	}

	public void addChartToCategory(Long categoryId, Long[] chartIds) {
		categoryService.addChartToCategory(categoryId, chartIds);
	}

	public Long addText(Text vo) throws BaseException {
		return textService.addText(vo);
	}

	public Long updText(Text vo) throws BaseException {
		return textService.updText(vo);
	}
	public void updTextParameter(List<Parameter> parameters)
			throws BaseException {
		textService.updTextParameter(parameters);
	}
	
	public void delText(Long pk) {
		textService.delText(pk);
	}

	public Text getText(Long pk) {
		return textService.getText(pk);
	}

	public List<Text> findAllText() {
		return textService.findAllText();
	}	

	public Parameter getParameter(Long pk) {
		return parameterService.getParameter(pk);
	}

	public Boolean findTextIsEntityByTextAndCategory(Long textId,
			Long categoryId) {
		return categoryService.findTextIsEntityByTextAndCategory(textId, categoryId);
	}

	public Boolean findChartIsEntityByChartAndCategory(Long chartId,
			Long categoryId) {
		return categoryService.findChartIsEntityByChartAndCategory(chartId, categoryId);
	}

	public Boolean findSessionIsEntityByParameterIdAndUserName(Long parameterId, String userName) {
		return parameterService.findSessionIsEntityByParameterIdAndUserName(parameterId, userName);
	}
}

