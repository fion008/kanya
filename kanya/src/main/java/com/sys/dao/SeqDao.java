package com.sys.dao;

import com.sys.base.dao.BaseDao;
import com.sys.entity.Seq;

public interface SeqDao extends BaseDao<Seq> {

	Seq getSeqByName(String name);

}