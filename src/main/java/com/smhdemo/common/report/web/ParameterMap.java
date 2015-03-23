package com.smhdemo.common.report.web;

import java.util.Map;

import com.smhdemo.common.report.entity.Text.Type;

/**
 * 
 *  
 * @author zhoudongchu
 */
public class ParameterMap {
	private Map<String, String> paraMap;
	private Type textType;
	public Map<String, String> getParaMap() {
		return paraMap;
	}
	public void setParaMap(Map<String, String> paraMap) {
		this.paraMap = paraMap;
	}
	public Type getTextType() {
		return textType;
	}
	public void setTextType(Type textType) {
		this.textType = textType;
	}
}

