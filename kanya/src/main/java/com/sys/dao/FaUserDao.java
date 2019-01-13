package com.sys.dao;

import com.sys.base.dao.BaseDao;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.Fa_user;

public interface FaUserDao extends BaseDao<Fa_user> {

	PageBean findFaUserList(String keywords, PageParam pageParam);

	Fa_user getFaUserByPhone(String phone);

	Fa_user getFaUserByAppUserId(Long appUserId);

}