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
public class DateInputTag extends SimpleTagSupport {
	private String name;
	private String format="yyyy-MM-dd";
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	 public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void doTag()throws JspException, IOException{
		 Writer out = getJspContext().getOut();
		 out.write("<input type='text' class='Wdate' size=20 theme='simple' name='"+name+"' onclick=WdatePicker({dateFmt:'"+format+"'})");	 
	 }
}

