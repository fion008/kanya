package com.sys.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sys.base.dao.impl.BaseDaoImpl;
import com.sys.dao.PlanDao;
import com.sys.entity.Plan;
import com.sys.response.PlanResp;

@Repository("planDao")
public class PlanDaoImpl extends BaseDaoImpl<Plan> implements PlanDao {

	@Override
	public PlanResp getPlanByPatientsId(Long patientsId) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (patientsId!=null) {
			paramMap.put("patientsId", patientsId);
		}
		return super.getSqlSession().selectOne("getPlanByPatientsId", paramMap);
	}

	@Override
	public int deletePlan(Long patientsId) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (patientsId!=null) {
			paramMap.put("patientsId", patientsId);
		}
		return super.getSqlSession().delete("deletePlan", paramMap);
	}

	@Override
	public List<Plan> getPlanByCurePlan(Long curePlanId) {
		return getEntityList(curePlanId, "cure_plan", Plan.class);
	}
}
