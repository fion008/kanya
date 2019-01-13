package com.sys.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sys.base.dao.impl.BaseDaoImpl;
import com.sys.base.tool.StringUtil;
import com.sys.dao.InterruptEventDao;
import com.sys.entity.Interrupt_event;
import com.sys.response.InterruptEventResp;

@Repository("interruptEventDao")
public class InterruptEventDaoImpl extends BaseDaoImpl<Interrupt_event>
		implements InterruptEventDao {

	@Override
	public List<InterruptEventResp> getInteEventByPatientsId(Long patientsId,
			Integer status, String date) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (patientsId != null) {
			paramMap.put("patientsId", patientsId);
		}
		if (status != null) {
			paramMap.put("status", status);
		}
		if (StringUtil.isNotBlank(date)) {
			paramMap.put("date", date);
		}
		return super.getSqlSession().selectList("getInteEventByPatientsId",
				paramMap);
	}

	@Override
	public Interrupt_event getEventByStatus(Long patientsId, Integer status) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (patientsId != null) {
			paramMap.put("patientsId", patientsId);
		}
		if (status != null) {
			paramMap.put("status", status);
		}
		return super.getSqlSession().selectOne("getEventByStatus", paramMap);
	}

	@Override
	public List<Interrupt_event> getNotDoInteEvent(Integer status) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (status != null) {
			paramMap.put("status", status);
		}
		return super.getSqlSession().selectList("getNotDoInteEvent", paramMap);
	}

}
