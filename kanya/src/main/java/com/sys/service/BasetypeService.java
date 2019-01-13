package com.sys.service;

import java.util.List;

import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.Basetype;

public interface BasetypeService {

	/**
	 * 查询列表分页
	 * 
	 * @param type
	 * @param keywords
	 * @param pageParam
	 * @return
	 */
	PageBean findBasetypeList(String type, String keywords, PageParam pageParam);

	/**
	 * 通过Id查询一条记录
	 * 
	 * @param id
	 * @return
	 */
	Basetype getBasetypeById(Long id);

	/**
	 * 新增或者修改
	 * 
	 * @param basetype
	 * @return
	 */
	int updateBasetype(Basetype basetype);

	/**
	 * 根据type获取列表
	 * 
	 * @param type
	 * @return
	 */
	List<Basetype> getBasetypeListByType(String type);

}
