package com.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.dao.CommonsDao;
import com.sys.service.CommonsService;

@Transactional
@Service("commonsService")
public class CommonServiceimpl implements CommonsService {

	@Autowired
	private CommonsDao commonsDao;

	@Override
	public int changeStatus(String tableName, String field, String value,String keyField,String keyValue) {
		return commonsDao.changeStatus(tableName, field, value,keyField,keyValue);
	}

}
