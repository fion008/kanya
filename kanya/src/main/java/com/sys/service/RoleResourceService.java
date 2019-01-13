package com.sys.service;

import java.util.List;

import com.sys.response.RoleResourceResp;

public interface RoleResourceService {
	/**
	 * 更新角色权限
	 * 
	 * @param roleResourceResp
	 * @return
	 */
	int updateRoleResource(List<RoleResourceResp> roleResourceRespList,Long roleId);
}
