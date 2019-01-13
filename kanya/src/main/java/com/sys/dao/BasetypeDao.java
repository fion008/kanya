package com.sys.dao;

import java.util.List;

import com.sys.base.dao.BaseDao;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.Basetype;

public interface BasetypeDao extends BaseDao<Basetype> {

	PageBean findBasetypeList(String type, String keywords, PageParam pageParam);

	List<Basetype> getBasetypeListByType(String type);

}