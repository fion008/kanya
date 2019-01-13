package com.sys.dao;

import java.util.List;

import com.sys.base.dao.BaseDao;
import com.sys.entity.History_plan;

public interface HistoryPlanDao extends BaseDao<History_plan> {

	List<History_plan> getHistoryPlanByPatientsId(Long patientsId);



}