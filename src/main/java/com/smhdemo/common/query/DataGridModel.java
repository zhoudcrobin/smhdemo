package com.smhdemo.common.query;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 *  
 * @author zhoudongchu
 */
public class DataGridModel {
    protected int page = 1;
    private int rows = 15;
    private String sort;
    private String order;
    private Map<String, String> parameters = new HashMap<String, String>();
    
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrder() {
		return order;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
}

