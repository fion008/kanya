package com.sys.service;

import java.util.List;

import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.Role;

public interface RoleService {

	/**
	 * 查询角色列表
	 * 
	 * @param keywords
	 * @param pageParam
	 * @return
	 */
	PageBean findRoleList(String keywords, PageParam pageParam);

	/**
	 * 新增角色
	 * 
	 * @param role
	 * @return
	 */
	int updateRole(Role role);

	/**
	 * 通过id获取角色信息
	 * 
	 * @param id
	 * @return
	 */
	Role getRole(Long id);
	
	
	/**
	 * 获取角色列表
	 * @return
	 */
	List<Role> getRoleList();

}
