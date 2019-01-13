package com.sys.dao;

import com.sys.base.dao.BaseDao;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.User;

public interface UserDao extends BaseDao<User> {
	/**
	 * 查询用户列表
	 * 
	 * @param keywords
	 * @param pageParam
	 * @return
	 */
	PageBean findUserList(String keywords, PageParam pageParam);

	/**
	 * 查询用户
	 * @param account
	 * @param type
	 * @return
	 */
	User findUserByAccount(String account, String type);

}