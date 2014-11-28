package com.smhdemo.common.query.jpa;

import java.util.ArrayList;
import java.util.List;

import com.smhdemo.common.query.Order;

/**
 * 查询条件对象
 *  
 * @author zhoudongchu
 */
public class QueryCondition {
	private int page=1;
	private int rows=15;
	private Order order = new Order();
	private String entityName;
	private List<QueryParameter> parameters = new ArrayList<QueryParameter>();
	public QueryCondition() {
	}

	public QueryCondition(int page, int rows, Order order, String entityName,
			List<QueryParameter> parameters) {
		this.page = page;
		this.rows = rows;
		this.order = order;
		this.entityName = entityName;
		this.parameters = parameters;
	}

	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public List<QueryParameter> getParameters() {
		return parameters;
	}
	public void setParameters(List<QueryParameter> parameters) {
		this.parameters = parameters;
	}
	public void add(QueryParameter queryParameter){
		this.parameters.add(queryParameter);
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
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}
