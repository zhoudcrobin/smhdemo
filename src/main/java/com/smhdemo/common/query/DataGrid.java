/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.smhdemo.common.query;

import java.util.Collections;
import java.util.List;

/**
 * Jquery easyui datagrid数据对象
 * 
 * @author zhoudongchu
 */
public class DataGrid {
    private Integer total;
    private List<?> rows;
    private List<?> footer;
    private List<String> errors;

    public DataGrid(Integer total,List<?> rows){
        this(total,rows,null);
    }
    
    public DataGrid(Integer total,List<?> rows,List<?> footer){
        this.total = total;
        this.rows = rows;
        this.footer = footer;
    }

    public DataGrid(List<String> errors){
        this.total =0;
        rows =Collections.emptyList();
        this.errors = errors;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<?> getFooter() {
        return footer;
    }

    public void setFooter(List<?> footer) {
        this.footer = footer;
    }    
}
