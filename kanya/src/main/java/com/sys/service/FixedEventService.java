package com.sys.service;

import java.util.List;

import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.Fixed_event;

public interface FixedEventService {

	/**
	 * 查询固定事件列表
	 * 
	 * @param keywords
	 * @param pageParam
	 * @return
	 */
	PageBean findFixedEventList(String keywords, PageParam pageParam);

	/**
	 * 固定事件
	 * 
	 * @param fixed_event
	 * @return
	 */
	int updateFixedEvent(Fixed_event fixed_event);

	/**
	 * 通过id获取固定事件
	 * 
	 * @param id
	 * @return
	 */
	Fixed_event getFixedEvent(Long id);

	/**
	 * 获取固定事件列表
	 * 
	 * @return
	 */
	List<Fixed_event> getFixedEventList();

}
