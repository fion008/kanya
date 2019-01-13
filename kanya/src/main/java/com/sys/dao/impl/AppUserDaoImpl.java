package com.sys.dao.impl;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.sys.base.dao.impl.BaseDaoImpl;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.dao.AppUserDao;
import com.sys.entity.App_user;

@Repository("appUserDao")
public class AppUserDaoImpl extends BaseDaoImpl<App_user> implements AppUserDao {

	public PageBean findAppUserList(String keywords, PageParam pageParam) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(keywords)) {
			paramMap.put("keywords", keywords);
		}
		return listPage(pageParam, paramMap, "findAppUserList");
	}

	@Override
	public App_user getAppUserByOpenId(String openId) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(openId)) {
			paramMap.put("openId", openId);
		}
		return super.getSqlSession().selectOne("getAppUserByOpenId", paramMap);
	}

	@Override
	public App_user getAppUserByPhone(String phone) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(phone)) {
			paramMap.put("phone", phone);
		}
		return super.getSqlSession().selectOne("getAppUserByPhone", paramMap);
	}

}
