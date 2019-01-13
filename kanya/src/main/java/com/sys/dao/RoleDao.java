package com.sys.dao;

import java.util.List;

import com.sys.base.dao.BaseDao;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.Role;

public interface RoleDao extends BaseDao<Role> {

	PageBean findRoleList(String keywords, PageParam pageParam);

	List<Role> getRoleList();
}