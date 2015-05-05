package com.smhdemo.common.tag;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 
 *  
 * @author zhoudongchu
 */
public class DateHeadTag extends SimpleTagSupport{
	private  String datehome;

	public String getDatehome() {
		return datehome;
	}

	public void setDatehome(String datehome) {
		this.datehome = datehome;
	}
	 public void doTag()throws JspException, IOException{
		 Writer out = getJspContext().getOut();
		 String url=datehome+"/WdatePicker.js";
		 out.write("<script src='"+url+"'></script>");	 
	 }
	
}

