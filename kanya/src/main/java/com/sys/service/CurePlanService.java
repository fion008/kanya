package com.sys.service;

import java.util.List;

import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.Cure_plan;

public interface CurePlanService {

	/**
	 * 查询矫正方案列表
	 * 
	 * @param keywords
	 * @param pageParam
	 * @return
	 */
	PageBean findCurePlanList(String keywords, PageParam pageParam);

	/**
	 * 新增/修改矫正方案
	 * 
	 * @param cure_plan
	 * @return
	 */
	int updateCurePlan(Cure_plan cure_plan);

	/**
	 * 通过id获取cure_plan信息
	 * 
	 * @param id
	 * @return
	 */
	Cure_plan getCurePlan(Long id);

	/**
	 * 所有的启用治疗方案
	 * 
	 * @return
	 */
	List<Cure_plan> getCurePlanList();
}
