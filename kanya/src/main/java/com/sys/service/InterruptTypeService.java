package com.sys.service;

import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.Interrupt_type;

public interface InterruptTypeService {

	/**
	 * 查询中断事件类型列表
	 * 
	 * @param keywords
	 * @param pageParam
	 * @return
	 */
	PageBean findInterruptTypeList(String keywords, PageParam pageParam);

	/**
	 * 新增/修改中断事件类型
	 * 
	 * @param interrupt_ype
	 * @return
	 */
	int updateInterruptType(Interrupt_type interrupt_ype);

	/**
	 * 通过id获取中断事件类型信息
	 * 
	 * @param id
	 * @return
	 */
	Interrupt_type getInterruptType(Long id);

}
