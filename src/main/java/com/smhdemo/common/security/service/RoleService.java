package com.smhdemo.common.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhdemo.common.base.BaseException;
import com.smhdemo.common.security.dao.RoleDao;
import com.smhdemo.common.security.entity.Role;
@Service
public class RoleService implements RoleServiceable {
	@Autowired
	protected RoleDao roleDao;
	
	@Override
	public Integer addRole(Role vo) throws BaseException{
		roleDao.persist(vo);
		return vo.getId();
	}

	@Override
	public Integer updRole(Role vo) throws BaseException{
		roleDao.merge(vo);
		return vo.getId();

	}

	@Override
	public void delRole(int pk) throws BaseException{
		roleDao.removeByPK(pk);

	}

	@Override
	public Role getRole(int pk) throws BaseException{
		return roleDao.get(pk);
	}

	@Override
	public List<Role> getAllRole() {
		return roleDao.getAllRole();
	}

}