package com.smhdemo.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhdemo.common.base.BaseException;
import com.smhdemo.common.security.entity.User;
import com.smhdemo.common.security.service.UserServiceable;

/**
 * 用户功能业务实现统一集
 *  
 * @author zhoudongchu
 */
@Service
public class SecurityFac implements SecurityFacable {
    @Autowired
    private UserServiceable userService;
	@Override
	public String addUser(User vo) throws BaseException{
		userService.addUser(vo);
		return vo.getAccountName();
	}

	@Override
	public String updUser(User vo) throws BaseException{
		userService.updUser(vo);
		return vo.getAccountName();
	}

	@Override
	public void delUser(String pk) throws BaseException{
		userService.delUser(pk);
	}

	@Override
	public User getUser(String pk) throws BaseException{
		return userService.getUser(pk);
	}

}
