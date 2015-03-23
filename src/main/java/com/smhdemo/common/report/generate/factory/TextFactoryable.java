/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.smhdemo.common.report.generate.factory;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhdemo.common.report.entity.Text;
import com.smhdemo.common.report.entity.Text.Type;
import com.smhdemo.common.report.generate.util.PageShowParam;

/**
 * 文字报表生成器
 * 
 * @author 吴智俊
 */
public interface TextFactoryable {

    /**
     * 报表生成
     *
     * @param parameters 页面传入参数列表
     * @param report 文字报表对象
     * @param type 报表类型
     * @param response
     * @param request
     * @return byte
     */
    public byte[] export(Map<String, String> parameters, Text report, Type type, HttpServletResponse response, HttpServletRequest request);

    /**
     * 查询报表参数并转换成PageShowParam对象集合
     *
     * @param report 文字报表对象
     * @return List
     */
    public List<PageShowParam> textParameters(Text report);
}
