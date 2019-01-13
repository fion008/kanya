package com.sys.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.sys.base.dao.impl.BaseDaoImpl;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.dao.FixedEventDao;
import com.sys.entity.Fixed_event;

@Repository("fixedEventDao")
public class FixedEventDaoImpl extends BaseDaoImpl<Fixed_event> implements
		FixedEventDao {

	@Override
	public PageBean findFixedEventList(String keywords, PageParam pageParam) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(keywords)) {
			paramMap.put("keywords", keywords);
		}
		return listPage(pageParam, paramMap, "findFixedEventList");
	}

	@Override
	public List<Fixed_event> getFixedEventList() {
		return super.getSqlSession().selectList("getFixedEventList");
	}
}
