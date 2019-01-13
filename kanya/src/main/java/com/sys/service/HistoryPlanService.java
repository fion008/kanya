package com.sys.service;

import java.util.List;

import com.sys.entity.History_plan;

public interface HistoryPlanService {


	/**
	 * 通过Id查询一条记录
	 * 
	 * @param id
	 * @return
	 */
	History_plan getHistoryPlanById(Long id);

	/**
	 * 新增或者修改
	 * 
	 * @param historyPlan
	 * @return
	 */
	int updateHistoryPlan(History_plan historyPlan);

	/**
	 * 根据 patientsId获取列表
	 * 
	 * @param  patientsId
	 * @return
	 */
	List<History_plan> getHistoryPlanByPatientsId(Long patientsId);

}
