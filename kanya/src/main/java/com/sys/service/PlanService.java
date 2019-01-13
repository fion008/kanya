package com.sys.service;

import java.util.List;

import com.sys.entity.Plan;
import com.sys.response.PlanResp;

public interface PlanService {

	/**
	 * 新增计划
	 * 
	 * @param plan
	 * @return
	 */
	int updatePlan(Plan plan);

	/**
	 * 通过id获取计划
	 * 
	 * @param id
	 * @return
	 */
	Plan getPlan(Long id);

	/**
	 * 通过患者查询计划
	 * 
	 * @param patientsId
	 * @return
	 */
	PlanResp getPlanByPatientsId(Long patientsId);

	/**
	 * 
	 * @param patientsId
	 * @return
	 */
	int deletePlan(Long patientsId);

	/**
	 * 查询治疗方案关联的计划
	 * 
	 * @param curePlanId
	 * @return
	 */
	List<Plan> getPlanByCurePlan(Long curePlanId);
}
