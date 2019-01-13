package com.sys.dao;

import com.sys.base.dao.BaseDao;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.Interrupt_type;

public interface InterruptTypeDao extends BaseDao<Interrupt_type> {

	PageBean findInterruptTypeList(String keywords, PageParam pageParam);
}