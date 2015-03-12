package com.smhdemo.web;
/**
 * 
 *  
 * @author zhoudongchu
 */
public abstract class BaseController {
	public String getForwardPage(String pageFileName){
		return getPagePath()+"/"+pageFileName;
	}
	protected abstract String getPagePath();
}

