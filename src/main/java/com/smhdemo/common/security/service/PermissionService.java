package com.smhdemo.common.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhdemo.common.base.BaseException;
import com.smhdemo.common.security.dao.PermissionDaoable;
import com.smhdemo.common.security.entity.Permission;
import com.smhdemo.common.security.entity.Role;
@Service
public class PermissionService implements PermissionServiceable {
	@Autowired
	protected PermissionDaoable permissionDao;
	@Override
	public Integer addPermission(Permission vo) throws BaseException {
		permissionDao.persist(vo);
		return vo.getId();
	}

	@Override
	public Integer updPermission(Permission vo) throws BaseException {
		permissionDao.merge(vo);
		return vo.getId();
	}

	@Override
	public void delPermission(int pk) throws BaseException {
		permissionDao.removeByPK(pk);
	}

	@Override
	public Permission getPermission(int pk) throws BaseException {
		return permissionDao.get(pk);
	}

}