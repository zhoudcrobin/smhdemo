package com.smhdemo.common.security.dao;

import org.springframework.stereotype.Repository;

import com.smhdemo.common.base.JpaDao;
import com.smhdemo.common.security.entity.User;

@Repository
public class UserDao extends JpaDao<String,User> implements UserDaoable {

}
