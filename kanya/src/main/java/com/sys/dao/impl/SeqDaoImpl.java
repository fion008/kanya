package com.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.sys.base.dao.impl.BaseDaoImpl;
import com.sys.dao.SeqDao;
import com.sys.entity.Seq;

@Repository("seqDao")
public class SeqDaoImpl extends BaseDaoImpl<Seq> implements SeqDao {

	@Override
	public Seq getSeqByName(String name) {
		return super.getSqlSession().selectOne("getSeqByName", name);
	}

}
