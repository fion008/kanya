package com.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.sys.base.dao.impl.BaseDaoImpl;
import com.sys.dao.CommonsDao;
import com.sys.entity.Commons;

@Repository("commonsDao")
public class CommonsDaoImpl extends BaseDaoImpl<Commons> implements CommonsDao {
}
