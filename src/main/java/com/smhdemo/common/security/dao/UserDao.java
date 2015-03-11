package com.smhdemo.common.security.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import com.smhdemo.common.base.JpaDao;
import com.smhdemo.common.security.entity.User;

@Repository
public class UserDao extends JpaDao<Integer,User> {

	public User getUser(String accountName) {
		String hql = "From User t Where t.accountName=:accountName and t.enabled=true";
		TypedQuery<User> query = this.getEntityManager().createQuery(hql, User.class);
		query.setParameter("accountName", accountName);
		User user = null;
		try{
			user = (User) query.getSingleResult();
		}catch(NoResultException e){
		}
		return user;
	}

}
