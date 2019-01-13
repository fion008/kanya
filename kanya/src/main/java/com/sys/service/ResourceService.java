package com.sys.service;

import java.util.List;

import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.Resource;
import com.sys.response.ResourceResp;
import com.sys.response.RoleResourceResp;

public interface ResourceService {

	/**
	 * 查询资源列表
	 * 
	 * @param keywords
	 * @param pageParam
	 * @return
	 */
	PageBean findResourceList(String keywords, PageParam pageParam);

	/**
	 * 新增资源
	 * 
	 * @param resource
	 * @return
	 */
	int updateResource(Resource resource);

	/**
	 * 通过id获取资源信息
	 * 
	 * @param id
	 * @return
	 */
	Resource getResource(Long id);

	/**
	 * 查询资源列表
	 * 
	 * @param type
	 * @return
	 */
	List<Resource> getResourceList(String type);

	/**
	 * 查询用户的菜单栏
	 * 
	 * @param userId
	 * @return
	 */
	List<ResourceResp> getResourceByUserId(Long userId);

	/**
	 * 通过角色查询角色资源列表
	 * 
	 * @param roleId
	 * @return
	 */
	List<RoleResourceResp> getResourceByRoleId(Long roleId);

}
