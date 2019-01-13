package com.sys.dao;

import com.sys.base.dao.BaseDao;
import com.sys.entity.Role_resource;

public interface RoleResourceDao extends BaseDao<Role_resource> {

	int deleteByRoleId(Long roleId);
}