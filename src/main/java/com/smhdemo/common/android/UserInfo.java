package com.smhdemo.common.android;

import java.io.Serializable;

/**
 * 
 *  
 * @author zhoudongchu
 */
public class UserInfo implements Serializable {
	private String name;
	private int age;
	private String sex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}

