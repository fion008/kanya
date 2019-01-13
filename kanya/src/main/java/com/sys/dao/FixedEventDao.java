package com.sys.dao;

import java.util.List;

import com.sys.base.dao.BaseDao;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.Fixed_event;

public interface FixedEventDao extends BaseDao<Fixed_event> {

	PageBean findFixedEventList(String keywords, PageParam pageParam);

	List<Fixed_event> getFixedEventList();
}