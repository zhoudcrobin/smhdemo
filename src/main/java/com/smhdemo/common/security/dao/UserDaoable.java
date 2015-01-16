package com.smhdemo.common.security.dao;

import com.smhdemo.common.base.JpaDaoable;
import com.smhdemo.common.security.entity.User;

public interface UserDaoable extends JpaDaoable<Integer, User> {
	User getUser(String accountName);
}
