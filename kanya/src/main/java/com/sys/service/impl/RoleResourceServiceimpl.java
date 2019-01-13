package com.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.dao.RoleResourceDao;
import com.sys.entity.Role_resource;
import com.sys.response.RoleResourceResp;
import com.sys.service.RoleResourceService;

@Transactional
@Service("roleResourceService")
public class RoleResourceServiceimpl implements RoleResourceService {
	@Autowired
	private RoleResourceDao roleResourceDao;

	@Override
	public int updateRoleResource(List<RoleResourceResp> roleResourceRespList,
			Long roleId) {
		int result = roleResourceDao.deleteByRoleId(roleId);
		Role_resource rr = null;
		for (RoleResourceResp roleResourceResp : roleResourceRespList) {
			rr = new Role_resource();
			rr.setResourceId(roleResourceResp.getId());
			rr.setRoleId(roleId);
			roleResourceDao.insert(rr);
		}
		return result;
	}
}
