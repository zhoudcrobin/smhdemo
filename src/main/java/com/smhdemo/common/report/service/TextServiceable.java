/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.smhdemo.common.report.service;

import java.util.List;
import java.util.Set;

import com.smhdemo.common.base.BaseException;
import com.smhdemo.common.report.entity.Parameter;
import com.smhdemo.common.report.entity.Text;
/**
 * 文字报表管理服务接口
 * 
 * @author zhoudongchu
 */
public interface TextServiceable {
	/**
	 * 保存报表文件并把报表中的参数存入数据库中
	 * 
	 * @param textReport 文字报表对象
	 * @return Long 文字报表编号
	 * @throws BaseException
	 */
	public Long addText(Text vo) throws BaseException;

	/**
	 * 修改报表文件
	 * 
	 * @param textReport 文字报表对象
	 * @return Long 文字报表编号
	 * @throws BaseException
	 */
	public Long updText(Text vo) throws BaseException;
	
	/**
	 * 修改报表参数
	 * 
	 * @param textReport 文字报表对象
	 * @return Long 文字报表编号
	 * @throws BaseException
	 */
	public void updTextParameter(List<Parameter> parameters) throws BaseException;
	/**
	 * 删除报表
	 * 
	 * @param textReportId 文字报表编号
	 */
	public void delText(Long pk);
	
	/**
	 * 查询报表
	 * 
	 * @param textReportId 文字报表编号
	 * @return TextReport 文字报表对象
	 */
	public Text getText(Long pk);
	
	/**
	 * 查询所有报表
	 * 
	 * @return List 文字报表集合
	 */
	public List<Text> findAllText() ;
}
