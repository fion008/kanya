package com.sys.service;

import com.sys.entity.Seq;

public interface SeqService {

	/**
	 * 通过Id查询
	 * 
	 * @param id
	 * @return
	 */
	Long getSeqByName(String name);

	/**
	 * 新增或者修改
	 * 
	 * @param basetype
	 * @return
	 */
	int updateSeq(Seq seq);

}
