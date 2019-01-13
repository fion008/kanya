package com.sys.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.sys.base.dao.impl.BaseDaoImpl;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.dao.BasetypeDao;
import com.sys.entity.Basetype;

@Repository("basetypeDao")
public class BasetypeDaoImpl extends BaseDaoImpl<Basetype> implements
		BasetypeDao {

	@Override
	public PageBean findBasetypeList(String type, String keywords,
			PageParam pageParam) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(keywords)) {
			paramMap.put("keywords", keywords);
		}
		if (StringUtils.isNotBlank(type)) {
			paramMap.put("type", type);
		}
		return listPage(pageParam, paramMap, "findBasetypeList");
	}

	@Override
	public List<Basetype> getBasetypeListByType(String type) {
		return super.getSqlSession().selectList("getBasetypeListByType", type);
	}
}
