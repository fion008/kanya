package com.sys.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.sys.base.dao.impl.BaseDaoImpl;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.dao.CurePlanDao;
import com.sys.entity.Cure_plan;

@Repository("curePlanDao")
public class CurePlanDaoImpl extends BaseDaoImpl<Cure_plan> implements CurePlanDao {

	@Override
	public PageBean findCurePlanList(String keywords, PageParam pageParam) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(keywords)) {
			paramMap.put("keywords", keywords);
		}
		return listPage(pageParam, paramMap, "findCurePlanList");
	}

	@Override
	public List<Cure_plan> getCurePlanList() {
		return getEntityList(0, "status", Cure_plan.class);
	}


}
