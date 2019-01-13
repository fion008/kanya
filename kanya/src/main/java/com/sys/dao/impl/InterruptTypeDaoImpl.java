package com.sys.dao.impl;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.sys.base.dao.impl.BaseDaoImpl;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.dao.InterruptTypeDao;
import com.sys.entity.Interrupt_type;

@Repository("interruptTypeDao")
public class InterruptTypeDaoImpl extends BaseDaoImpl<Interrupt_type> implements InterruptTypeDao {

	@Override
	public PageBean findInterruptTypeList(String keywords, PageParam pageParam) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(keywords)) {
			paramMap.put("keywords", keywords);
		}
		return listPage(pageParam, paramMap, "findInterruptTypeList");
	}


}
