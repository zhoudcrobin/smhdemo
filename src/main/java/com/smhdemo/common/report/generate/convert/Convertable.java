/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.smhdemo.common.report.generate.convert;

/**
 * 字符串和指定数据类型相互转换
 *    
 * @author WangWei
 */
public interface Convertable<T> {

    /**
     * 转换参数数据类型
     * 
     * @param value 转换的�?
     * @return 转换后的�?     
     */
     public T parse(String value)throws ConvertException;
    
    /**
     * 将指定的数据类型转换成字符串
     * 
     * @param value 数据�?
     * @return 字符�?
     */
    public String parseString(T value);
}
