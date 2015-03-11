package com.smhdemo.common.security.dao;

import org.springframework.stereotype.Repository;

import com.smhdemo.common.base.JpaDao;
import com.smhdemo.common.security.entity.Permission;
@Repository
public class PermissionDao extends JpaDao<Integer, Permission>{

}
