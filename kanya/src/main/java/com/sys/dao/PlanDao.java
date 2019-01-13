package com.sys.dao;

import java.util.List;

import com.sys.base.dao.BaseDao;
import com.sys.entity.Plan;
import com.sys.response.PlanResp;

public interface PlanDao extends BaseDao<Plan> {

	PlanResp getPlanByPatientsId(Long patientsId);

	int deletePlan(Long patientsId);

	List<Plan> getPlanByCurePlan(Long curePlanId);

}