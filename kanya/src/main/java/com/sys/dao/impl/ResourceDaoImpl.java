package com.sys.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.sys.base.dao.impl.BaseDaoImpl;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.dao.ResourceDao;
import com.sys.entity.Resource;
import com.sys.response.ResourceResp;
import com.sys.response.RoleResourceResp;

@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements
		ResourceDao {

	@Override
	public PageBean findResourceList(String keywords, PageParam pageParam) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(keywords)) {
			paramMap.put("keywords", keywords);
		}
		return listPage(pageParam, paramMap, "findResourceList");
	}

	@Override
	public List<Resource> getResourceList(String type) {
		return super.getSqlSession().selectList("getResourceList", type);
	}

	@Override
	public List<ResourceResp> getResourceByRoleId(Long roleId, Long pid) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (roleId != null) {
			paramMap.put("roleId", roleId);
		}
		if (pid != null) {
			paramMap.put("pid", pid);
		}
		return super.getSqlSession()
				.selectList("getResourceByRoleId", paramMap);
	}

	@Override
	public List<RoleResourceResp> getAllResourceByRoleId(Long roleId,Long pid) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (roleId != null) {
			paramMap.put("roleId", roleId);
		}
		if (pid != null) {
			paramMap.put("pid", pid);
		}
		return super.getSqlSession()
				.selectList("getAllResourceByRoleId", paramMap);
	}
}
