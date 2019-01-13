package com.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.base.exception.BaseException;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.dao.ResourceDao;
import com.sys.dao.UserDao;
import com.sys.entity.Resource;
import com.sys.entity.User;
import com.sys.response.ResourceResp;
import com.sys.response.RoleResourceResp;
import com.sys.service.ResourceService;

@Transactional
@Service("resourceService")
public class ResourceServiceimpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private UserDao userDao;

	@Override
	public PageBean findResourceList(String keywords, PageParam pageParam) {
		return resourceDao.findResourceList(keywords, pageParam);
	}

	@Override
	public int updateResource(Resource resource) {
		if (resource.getId() != null) {
			Resource exist = resourceDao.getByKey(resource.getId(),
					Resource.class);
			resource.setStatus(exist.getStatus());
			return resourceDao.update(resource);
		} else {
			return resourceDao.insert(resource);
		}
	}

	@Override
	public Resource getResource(Long id) {
		return resourceDao.getByKey(id, Resource.class);
	}

	@Override
	public List<Resource> getResourceList(String type) {
		return resourceDao.getResourceList(type);
	}

	@Override
	public List<ResourceResp> getResourceByUserId(Long userId) {
		User user = userDao.getByKey(userId, User.class);
		if (user == null) {
			throw new BaseException(1111, "参数错误");
		}
		List<ResourceResp> respList = resourceDao.getResourceByRoleId(
				user.getRoleId(), 0l);
		List<ResourceResp> childList = null;
		if (respList != null) {
			for (ResourceResp resp : respList) {
				childList = resourceDao.getResourceByRoleId(user.getRoleId(),
						resp.getId());
				if (childList != null) {
					resp.setChildren(childList);
				}
			}
		}
		return respList;
	}

	@Override
	public List<RoleResourceResp> getResourceByRoleId(Long roleId) {
		List<RoleResourceResp> rrList= resourceDao.getAllResourceByRoleId(roleId,0L);
		List<RoleResourceResp> childList = null;
		if (rrList != null) {
			for (RoleResourceResp resp : rrList) {
				childList = resourceDao.getAllResourceByRoleId(roleId,
						resp.getId());
				if (childList != null) {
					resp.setChildren(childList);
				}
			}
		}
		 
		 return rrList;
	}

}
