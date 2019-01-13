package com.sys.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.sys.base.dao.impl.BaseDaoImpl;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.dao.RoleDao;
import com.sys.entity.Role;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	@Override
	public PageBean findRoleList(String keywords, PageParam pageParam) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(keywords)) {
			paramMap.put("keywords", keywords);
		}
		return listPage(pageParam, paramMap, "findRoleList");
	}

	@Override
	public List<Role> getRoleList() {
		return super.getSqlSession().selectList("getRoleList");
	}
}
