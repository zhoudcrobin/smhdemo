package com.smhdemo.common.query.jpa;

/**
 * 查询参数对象
 *  
 * @author zhoudongchu
 */
public class QueryParameter {
	public enum QueryOperateType{
		Equal,CharIn,In,Gt,Lt,Between
	}
	
	private String name;
	private Object[] value;
	private QueryOperateType type;
	
	public QueryParameter(String name, Object[] value, QueryOperateType type) {
		this.name = name;
		this.value = value;
		this.type = type;
	}
	public QueryParameter(String name, Object value, QueryOperateType type) {
		this.name = name;
		Object[] valueArr={value};
		this.value = valueArr;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object[] getValue() {
		return value;
	}
	public void setValue(Object[] value) {
		this.value = value;
	}
	public QueryOperateType getType() {
		return type;
	}
	public void setType(QueryOperateType type) {
		this.type = type;
	}
}
