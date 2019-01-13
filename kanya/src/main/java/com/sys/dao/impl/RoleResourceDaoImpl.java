package com.sys.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.sys.base.dao.impl.BaseDaoImpl;
import com.sys.dao.RoleResourceDao;
import com.sys.entity.Role_resource;

@Repository("roleResourceDao")
public class RoleResourceDaoImpl extends BaseDaoImpl<Role_resource> implements
		RoleResourceDao {

	@Override
	public int deleteByRoleId(Long roleId) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (roleId != null) {
			paramMap.put("roleId", roleId);
		}
		return super.getSqlSession().delete("deleteByRoleId", paramMap);
	}
}
