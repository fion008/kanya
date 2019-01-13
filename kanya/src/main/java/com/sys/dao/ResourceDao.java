package com.sys.dao;

import java.util.List;

import com.sys.base.dao.BaseDao;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.Resource;
import com.sys.response.ResourceResp;
import com.sys.response.RoleResourceResp;

public interface ResourceDao extends BaseDao<Resource> {

	PageBean findResourceList(String keywords, PageParam pageParam);

	List<Resource> getResourceList(String type);

	List<ResourceResp> getResourceByRoleId(Long roleId, Long pid);

	List<RoleResourceResp> getAllResourceByRoleId(Long roleId,Long pid);
}