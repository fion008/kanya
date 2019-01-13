package com.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.dao.FaUserDao;
import com.sys.entity.Fa_user;
import com.sys.service.FaUserService;

@Transactional
@Service("faUserService")
public class FaUserServiceimpl implements FaUserService {
	@Autowired
	private FaUserDao faUserDao;

	@Override
	public PageBean findFaUserList(String keywords, PageParam pageParam) {
		return faUserDao.findFaUserList(keywords, pageParam);
	}

	@Override
	public int updateFaUser(Fa_user fa_User) {
		if (fa_User.getId() != null) {
			Fa_user exist = faUserDao.getByKey(fa_User.getId(), Fa_user.class);
			fa_User.setStatus(exist.getStatus());
			fa_User.setApp_user_id(exist.getApp_user_id());
			return faUserDao.update(fa_User);
		} else {
			return faUserDao.insert(fa_User);
		}
	}

	@Override
	public Fa_user getFaUser(Long id) {
		return faUserDao.getByKey(id, Fa_user.class);
	}

	@Override
	public Fa_user getFaUserByPhone(String phone) {
		return faUserDao.getFaUserByPhone(phone);
	}

	@Override
	public Fa_user getFaUserByAppUserId(Long appUserId) {
		return faUserDao.getFaUserByAppUserId(appUserId);
	}
}
