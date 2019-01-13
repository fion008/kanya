package com.sys.dao;

import java.util.List;

import com.sys.base.dao.BaseDao;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.Cure_plan;

public interface CurePlanDao extends BaseDao<Cure_plan> {

	PageBean findCurePlanList(String keywords, PageParam pageParam);

	List<Cure_plan> getCurePlanList();
}