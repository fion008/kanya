package com.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.dao.RoleDao;
import com.sys.entity.Role;
import com.sys.service.RoleService;

@Transactional
@Service("roleService")
public class RoleServiceimpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public PageBean findRoleList(String keywords, PageParam pageParam) {
		return roleDao.findRoleList(keywords, pageParam);
	}

	@Override
	public int updateRole(Role role) {
		if (role.getId() != null) {
			Role exist = roleDao.getByKey(role.getId(), Role.class);
			exist.setName(role.getName());
			exist.setDescription(role.getDescription());
			return roleDao.update(exist);
		} else {
			return roleDao.insert(role);
		}
	}

	@Override
	public Role getRole(Long id) {
		return roleDao.getByKey(id, Role.class);
	}

	@Override
	public List<Role> getRoleList() {
		return roleDao.getRoleList();
	}

}
