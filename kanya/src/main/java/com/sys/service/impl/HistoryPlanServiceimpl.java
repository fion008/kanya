package com.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.dao.HistoryPlanDao;
import com.sys.entity.History_plan;
import com.sys.service.HistoryPlanService;

@Transactional
@Service("historyPlanService")
public class HistoryPlanServiceimpl implements HistoryPlanService {

	@Autowired
	private HistoryPlanDao historyPlanDao;


	@Override
	public History_plan getHistoryPlanById(Long id) {
		return historyPlanDao.getByKey(id, History_plan.class);
	}

	@Override
	public int updateHistoryPlan(History_plan historyplan) {
		if (historyplan.getId() != null) {
			History_plan basetypeExist = historyPlanDao.getByKey(historyplan.getId(),
					History_plan.class);
			return historyPlanDao.update(basetypeExist);
		} else {
			return historyPlanDao.insert(historyplan);
		}
	}

	@Override
	public List<History_plan> getHistoryPlanByPatientsId(Long patientsId) {
		return historyPlanDao.getHistoryPlanByPatientsId(patientsId);
	}


}
