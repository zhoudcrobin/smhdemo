package com.smhdemo.common.security.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="shiro_user")
public class User implements Serializable {
	private static final long serialVersionUID = -6551089922634688550L;
	  
    @Id
    @Column(name="accountname",length=32)
    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9]{6,20}", message = "账户名是由数字字母组成的6到20位的字符串") 
    private String accountName;
    
    @Column(name="age")
    private Integer age;
    
    @Column(name="nicename",length=32)
    private String niceName;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getNiceName() {
		return niceName;
	}

	public void setNiceName(String niceName) {
		this.niceName = niceName;
	}
    
}
