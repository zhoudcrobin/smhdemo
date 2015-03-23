/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.smhdemo.common.report.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.engine.JRParameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.smhdemo.common.base.BaseException;
import com.smhdemo.common.report.dao.CategoryDao;
import com.smhdemo.common.report.dao.ParameterDao;
import com.smhdemo.common.report.dao.TextDao;
import com.smhdemo.common.report.entity.Category;
import com.smhdemo.common.report.entity.Parameter;
import com.smhdemo.common.report.entity.Text;
import com.smhdemo.common.report.util.ParameterSetValueUtil;
import com.smhdemo.common.report.util.TextDesignUtil;
/**
 * 
 * @author zhoudongchu
 *
 */
@Service
public class TextService implements TextServiceable {

	@Autowired
	private TextDao textDao;
	@Autowired
	private CategoryDao categorDao;
	@Autowired
	private ParameterDao parameterDao;
	
	@Override
	public Long addText(Text vo) throws BaseException {
		byte[] reportFile = vo.getTextEntity();

		if (reportFile != null && reportFile.length > 0) {
			InputStream in = new ByteArrayInputStream(reportFile);
			TextDesignUtil rd = new TextDesignUtil(in);
			List<JRParameter> paramList = rd.getParameters();
			
			Set<Parameter> icSet = new LinkedHashSet<Parameter>();
			if (!paramList.isEmpty()) {
				for (JRParameter param : paramList) {
					Parameter ic = getParameterValue(param);
					icSet.add(ic);
				}
				vo.setParameters(icSet);
			}
		}
		textDao.persist(vo);
		return vo.getId();
	}

	@Override
	public Long updText(Text vo) throws BaseException {
		Text entity = textDao.get(vo.getId());
		
//		entity.setBaseDS(textReport.getBaseDS());
		entity.setName(vo.getName());
		entity.setHidden(vo.getHidden());
		entity.setRemark(vo.getRemark());
		entity.setUpdateDate(new Date(Calendar.getInstance().getTime().getTime()));
		byte[] reportFile = vo.getTextEntity();
		if (reportFile != null && reportFile.length > 0) {
			entity.setTextEntity(vo.getTextEntity());
			
			InputStream in = new ByteArrayInputStream(reportFile);
			TextDesignUtil rd = new TextDesignUtil(in);

			List<JRParameter> paramList = rd.getParameters();
			Set<Parameter> icSet = entity.getParameters();

			Set<Parameter> icNewList = new LinkedHashSet<Parameter>();
			for (JRParameter param : paramList) {
				Parameter ic = findListEntity(icSet, param);
				if (ic == null) {
					ic = getParameterValue(param);
				}
				icNewList.add(ic);
			}
			entity.setParameters(icNewList);
		}
		textDao.merge(entity);
		return entity.getId();

	}

	@Override
	public void updTextParameter(Long textId, List<Parameter> parameters)
			throws BaseException {
		Text entity = textDao.get(textId);
		Set<Parameter> paramSet = new LinkedHashSet<Parameter>();
		for(Parameter vo:parameters){
			Parameter oldvo = parameterDao.get(vo.getId());
			oldvo.setCnName(vo.getCnName());
			oldvo.setDefaultValue(vo.getDefaultValue());
			oldvo.setType(vo.getType());
			oldvo.setValue(vo.getValue());
			paramSet.add(oldvo);
		}
		entity.setParameters(paramSet);
		textDao.merge(entity);
	}

	@Override
	public void delText(Long pk) {
		Text text = textDao.get(pk);
		Assert.notNull(text);
		List<Category> categories = textDao.findCategoryReportByTextReportId(pk);
		if (categories != null && !categories.isEmpty()){
			for (Category category : categories){
				Set<Text> texts = category.getTextReports();
				if (texts.isEmpty()) continue;
				texts.remove(text);
				category.setTextReports(texts);
				categorDao.merge(category);
			}
		}
//		List<EwcmsJobReport> ewcmsJobReports = textDao.findEwcmsJobReportByTextReportId(textReportId);
//		if (ewcmsJobReports != null && !ewcmsJobReports.isEmpty()){
//			for (EwcmsJobReport ewcmsJobReport : ewcmsJobReports){
//				if (ewcmsJobReport.getChartReport() == null){
//					ewcmsJobReportDAO.remove(ewcmsJobReport);
//				}else{
//					ewcmsJobReport.setTextReport(null);
//					ewcmsJobReportDAO.merge(ewcmsJobReport);
//				}
//			}
//		}
		textDao.removeByPK(pk);
		
	}

	@Override
	public Text getText(Long pk) {
		return textDao.get(pk);
	}

	@Override
	public List<Text> findAllText() {
		return textDao.findAll();
	}

	@Override
	public Long updTextParameter(Long textId, Parameter parameter)
			throws BaseException {
		if (textId == null || textId.intValue() == 0)
			throw new BaseException("", "报表编号不存在，请重新选择！");
		Text text = textDao.get(textId);
		if (text == null)
			throw new BaseException("", "报表不存在，请重新选择！");
		
		parameter = ParameterSetValueUtil.setParametersValue(parameter);
		
		Set<Parameter> parameters = text.getParameters();
		parameters.remove(parameter);
		parameters.add(parameter);
		text.setParameters(parameters);
		
		textDao.merge(text);
		
		return parameter.getId();
	}
	
	/**
	 * 把报表文件里的参数转换数据参数
	 * 
	 * @param jrParameter
	 *            报表参数对象
	 * @return Parameters
	 */
	private Parameter getParameterValue(JRParameter jrParameter) {
		Parameter ic = new Parameter();

		ic.setEnName(jrParameter.getName());
		ic.setClassName(jrParameter.getValueClassName());
		if (jrParameter.getDefaultValueExpression() == null){
			ic.setDefaultValue("");
		}else{
			ic.setDefaultValue(jrParameter.getDefaultValueExpression().getText());
		}
		ic.setDescription(jrParameter.getDescription());
		ic.setType(Conversion(jrParameter.getValueClassName()));

		return ic;
	}

	/**
	 * 根据报表参数名查询数据库中的报表参数集合
	 * 
	 * @param icSet
	 *            数据库中的报表参数集合
	 * @param JRParameter
	 *            报表参数
	 * @return ReportParameter
	 */
	private Parameter findListEntity(Set<Parameter> icSet, JRParameter param) {
		for (Parameter ic : icSet) {
			String rpEnName = ic.getEnName();
			String jrEnName = param.getName();
			if (jrEnName.trim().equals(rpEnName.trim())) {
				return ic;
			}
		}
		return null;
	}

	/**
	 * 把类型名转换成枚举
	 * 
	 * @param className
	 *            类型名
	 * @return InputControlEnum 枚举
	 */
	private Parameter.Type Conversion(String className) {
		if (className.toLowerCase().indexOf("boolean") > -1) {
			return Parameter.Type.BOOLEAN;
		}
		return Parameter.Type.TEXT;
	}

}
