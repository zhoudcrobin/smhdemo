package com.smhdemo.common.security.dao;

import org.springframework.stereotype.Repository;

import com.smhdemo.common.base.JpaDao;
import com.smhdemo.common.security.entity.LoginLog;

/**
 * 
 *  
 * @author zhoudongchu
 */
@Repository
public class LoginLogDao extends JpaDao<Integer, LoginLog>{

}
