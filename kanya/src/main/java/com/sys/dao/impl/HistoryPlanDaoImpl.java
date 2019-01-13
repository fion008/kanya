package com.sys.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sys.base.dao.impl.BaseDaoImpl;
import com.sys.dao.HistoryPlanDao;
import com.sys.entity.History_plan;

@Repository("historyPlanDao")
public class HistoryPlanDaoImpl extends BaseDaoImpl<History_plan> implements HistoryPlanDao {

	@Override
	public List<History_plan> getHistoryPlanByPatientsId(Long patientsId) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (patientsId!=null) {
			paramMap.put("patientsId", patientsId);
		}
		return super.getSqlSession().selectList("getHistoryPlanByPatientsId", paramMap);
	}
	
	
}
