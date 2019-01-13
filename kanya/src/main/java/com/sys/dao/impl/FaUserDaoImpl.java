package com.sys.dao.impl;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.sys.base.dao.impl.BaseDaoImpl;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.dao.FaUserDao;
import com.sys.entity.Fa_user;

@Repository("faUserDao")
public class FaUserDaoImpl extends BaseDaoImpl<Fa_user> implements FaUserDao {

	@Override
	public PageBean findFaUserList(String keywords, PageParam pageParam) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(keywords)) {
			paramMap.put("keywords", keywords);
		}
		return listPage(pageParam, paramMap, "findFaUserList");
	}

	@Override
	public Fa_user getFaUserByPhone(String phone) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(phone)) {
			paramMap.put(" phone", phone);
		}
		return super.getSqlSession().selectOne("getFaUserByPhone", paramMap);
	}

	@Override
	public Fa_user getFaUserByAppUserId(Long appUserId) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (appUserId != null) {
			paramMap.put("appUserId", appUserId);
		}
		return super.getSqlSession()
				.selectOne("getFaUserByAppUserId", paramMap);

	}

}
