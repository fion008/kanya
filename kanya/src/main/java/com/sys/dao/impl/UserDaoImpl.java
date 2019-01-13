package com.sys.dao.impl;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.sys.base.dao.impl.BaseDaoImpl;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.dao.UserDao;
import com.sys.entity.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public PageBean findUserList(String keywords,PageParam pageParam) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(keywords)) {
			paramMap.put("keywords", keywords);
		}
		return listPage(pageParam, paramMap, "findUserList");
	}
	

	@Override
	public User findUserByAccount(String account, String type) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(account)) {
			paramMap.put("account", account);
		}
		if (StringUtils.isNotBlank(type)) {
			paramMap.put("type", type);
		}
		return super.getSqlSession().selectOne("findUserByAccount", paramMap);
	}


}
