package com.smhdemo.common.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

import com.smhdemo.common.security.entity.User;

/**
 * 
 *  
 * @author zhoudongchu
 */
public final class Md5Utils {
    /**
     * 对密码进行md5加密,并返回密文和salt，包含在User对象中
     * @param username 用户名
     * @param password 密码
     * @return 密文和salt
     */
    public static User md5Password(String userName,String password){
    	if(userName==null||userName.length()==0){
    		return new User();
    	}
    	if(password==null||password.length()==0){
    		return new User();
    	}
        SecureRandomNumberGenerator secureRandomNumberGenerator=new SecureRandomNumberGenerator();
        String salt= secureRandomNumberGenerator.nextBytes().toHex();
        //组合username,两次迭代，对密码进行加密
        String password_cipherText= new Md5Hash(password,userName+salt,2).toHex();
        User user=new User();
        user.setPassword(password_cipherText);
        user.setSalt(salt);
        user.setAccountName(userName);
        return user;
    }
    
    /**
     * 通过username,password,salt,校验密文是否匹配 ，校验规则其实在配置文件中，这里为了清晰，写下来
     * @param username 用户名
     * @param password 原密码
     * @param salt  盐
     * @param md5cipherText 密文
     * @return
     */
    public static  boolean checkMd5Password(String userName,String password,String salt,String md5cipherText)
    {
    	if(userName==null||userName.length()==0){
    		return false;
    	}
    	if(password==null||password.length()==0){
    		return false;
    	}
    	if(md5cipherText==null||md5cipherText.length()==0){
    		return false;
    	}
        //组合username,两次迭代，对密码进行加密s
        String password_cipherText= new Md5Hash(password,userName+salt,2).toHex();
        return md5cipherText.equals(password_cipherText);
    }
}

