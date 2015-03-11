package com.smhdemo.common.security.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.smhdemo.common.base.JpaDao;
import com.smhdemo.common.security.entity.Role;
@Repository
public class RoleDao extends JpaDao<Integer, Role>{

	public List<Role> getAllRole() {
		String hql = "From Role t";
		TypedQuery<Role> query = this.getEntityManager().createQuery(hql, Role.class);
		return query.getResultList();
	}
	
}