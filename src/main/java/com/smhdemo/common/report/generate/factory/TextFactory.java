/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.smhdemo.common.report.generate.factory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRParameter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.smhdemo.common.base.BaseRuntimeException;
import com.smhdemo.common.datasource.entity.Base;
import com.smhdemo.common.datasource.generate.factory.DataSourceFactoryable;
import com.smhdemo.common.datasource.generate.factory.init.InitDataSourceFactoryable;
import com.smhdemo.common.datasource.generate.service.DataSourceServiceable;
import com.smhdemo.common.report.entity.Parameter;
import com.smhdemo.common.report.entity.Text;
import com.smhdemo.common.report.entity.Text.Type;
import com.smhdemo.common.report.generate.convert.ConvertFactory;
import com.smhdemo.common.report.generate.service.text.BaseTextGenerateServiceable;
import com.smhdemo.common.report.generate.service.text.HtmlGenerateService;
import com.smhdemo.common.report.generate.service.text.PdfGenerateService;
import com.smhdemo.common.report.generate.service.text.RtfGenerateService;
import com.smhdemo.common.report.generate.service.text.XlsGenerateService;
import com.smhdemo.common.report.generate.service.text.XmlGenerateService;
import com.smhdemo.common.report.generate.util.PageShowParam;
import com.smhdemo.common.report.generate.util.ParamConversionPage;
/**
 * @author 吴智俊
 */
@Service
public class TextFactory implements TextFactoryable {

	private static final Logger logger = LoggerFactory.getLogger(TextFactory.class);
	
    private static Map<String, BaseTextGenerateServiceable> textReportMap;

    static {
        textReportMap = new HashMap<String, BaseTextGenerateServiceable>();

        textReportMap.put("HTML", new HtmlGenerateService());
        textReportMap.put("PDF", new PdfGenerateService());
        textReportMap.put("XLS", new XlsGenerateService());
        textReportMap.put("RTF", new RtfGenerateService());
        textReportMap.put("XML", new XmlGenerateService());
    }
    @Autowired
    private DataSource dataSource;
    @Autowired
    private InitDataSourceFactoryable initDataSourceFactory;


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @SuppressWarnings("unchecked")
	public byte[] export(Map<String, String> parameters, Text report, Type type, HttpServletResponse response, HttpServletRequest request) {
        DataSourceServiceable service = null;
        try {
            if (report == null) {
                return null;
            }
            
            // 根据传入的报表类型,取得相应的报表引擎类
            BaseTextGenerateServiceable textEngineClass = textReportMap.get(type.name());
            if (textEngineClass == null) {
                return null;
            }

            // 取得报表文件
            byte[] textReportEntity = report.getTextEntity();
            InputStream in = new ByteArrayInputStream(textReportEntity);
            // 把传入的String类型的参数，转换成报表指定类型参数
            Set<Parameter> list = report.getParameters();
            Map<String, Object> textParam = new HashMap<String, Object>();
            if (parameters == null || parameters.size() == 0){
	            for (Parameter param : list) {
	                String value = param.getDefaultValue();
	                if (value == null) {
	                    continue;
	                }
		            String className = param.getClassName();
		            Class<Object> forName = (Class<Object>)Class.forName(className);
		            Object paramValue = ConvertFactory.instance.convertHandler(forName).parse(value);
		            
	                textParam.put(param.getEnName(), paramValue);
	            }
            }else{
	            for (Parameter param : list) {
	                String value = parameters.get(param.getEnName());
	                if (value == null) {
	                    continue;
	                }
		            String className = param.getClassName();
		            Class<Object> forName = (Class<Object>)Class.forName(className);
		            Object paramValue = ConvertFactory.instance.convertHandler(forName).parse(value);
		            
	                textParam.put(param.getEnName(), paramValue);
	            }
            }
//            textParam.put(JRParameter.REPORT_CONNECTION, dataSource.getConnection());
            // 取得数据源
            Base alqcDataSource = report.getBaseDS();
            if (alqcDataSource == null) {
                // 使用系统数据源
                textParam.put(JRParameter.REPORT_CONNECTION, dataSource.getConnection());
            } else {
                // 使用外部数据
                DataSourceFactoryable factory = (DataSourceFactoryable) initDataSourceFactory.getBean(alqcDataSource.getClass());
                service = factory.createService(alqcDataSource);
                textParam.put(JRParameter.REPORT_CONNECTION, service.openConnection());
            }

            return textEngineClass.export(in, type, textParam, response, request);
        } catch (Exception e) {
            logger.error("Text Export Exception", e);
            throw new BaseRuntimeException(e.toString());
        } finally {
            if (service != null) {
                service.closeConnection();
            }
        }
    }

    public List<PageShowParam> textParameters(Text report) {
        Assert.notNull(report);
        Set<Parameter> paramSet = report.getParameters();
        return ParamConversionPage.conversion(paramSet);
    }
}
